package com.sde.assigment.home

import android.content.Context
import android.util.Log
import com.sde.assigment.dagger.getInjector
import com.sde.assigment.data.DataManager
import com.sde.assigment.data.interfaces.ApiCallback
import com.sde.assigment.data.interfaces.RoomDataFetchCallback
import com.sde.assigment.data.interfaces.RoomDataSaveCallback
import com.sde.assigment.models.FacilitiesAndExclusionsModel
import com.sde.assigment.data.sharedpref.AppPref
import com.sde.assigment.models.Exclusion
import com.sde.assigment.models.Facility
import com.sde.assigment.utils.isOutdatedCache
import com.sde.assigment.utils.showToast
import javax.inject.Inject

class HomePresenter(private val mView: HomeContract.View) : HomeContract.Presenter, ApiCallback,
    RoomDataSaveCallback, RoomDataFetchCallback {
    private val tag="HomePresenter"
    @Inject
    lateinit var dataManager: DataManager
    @Inject
    lateinit var appPref: AppPref


    override fun start() {
        getInjector(mView as Context).inject(this)
        mView.init()
    }

    override fun loadData() {
        val isOutdatedCache = appPref.lastUpdated.isOutdatedCache()
        Log.e(tag, "isOutdatedCache: $isOutdatedCache")
        if (isOutdatedCache) {
            Log.e(tag, "calling Network API")
            if(appPref.lastUpdated!=-1L) {
                Log.e(tag, "clear LocalData")
                dataManager.clearLocalData()
            }else{
                Log.e(tag, "No LocalData till now")
            }
            dataManager.loadDataFromAPI(this)
        } else {
            Log.e(tag, "getting from Local Room")
            dataManager.loadDataFromRoomDB(this)
        }
    }

    override fun onApiSuccess(t: FacilitiesAndExclusionsModel) {
        Log.e(tag, "onApiSuccess")
        mView.loadRecyclerViewData(t)
        dataManager.saveLocalData(t,this)
    }

    override fun onApiError(message: String) {
        Log.e(tag, "onApiError message: $message")
        mView.showError(message)
    }

    override fun onSaveSuccess() {
        Log.e(tag, "onSaveSuccess")
        appPref.lastUpdated = System.currentTimeMillis()
        Log.e(tag, "onSaveSuccess lastUpdated: ${appPref.lastUpdated}")
    }

    override fun onSaveError(message: String) {
        Log.e(tag, "onSaveError message: $message")
        message.showToast(mView as Context)
    }

    override fun onFetchSuccess(facilities: List<Facility>, exclusions: List<Exclusion>) {
        Log.e(tag, "onFetchSuccess Success: facilities : ${facilities.size} ,exclusions : ${exclusions.size}")
        if (!facilities.isNullOrEmpty() && !exclusions.isNullOrEmpty()) {
            val model = FacilitiesAndExclusionsModel(facilities, arrayListOf(exclusions))
            mView.loadRecyclerViewData(model)
        } else {
            Log.e(tag, "Local Room Database empty. So Loading Data from Network")
            dataManager.loadDataFromAPI(this)
        }
    }

    override fun onFetchError(message: String) {
        Log.e(tag, "onSaveError message: $message")
        message.showToast(mView as Context)
    }


}