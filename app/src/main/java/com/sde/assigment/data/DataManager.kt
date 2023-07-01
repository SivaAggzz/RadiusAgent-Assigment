package com.sde.assigment.data

import android.util.Log
import com.sde.assigment.callback.Callback
import com.sde.assigment.data.interfaces.ApiCallback
import com.sde.assigment.data.interfaces.RoomDataFetchCallback
import com.sde.assigment.data.interfaces.RoomDataSaveCallback
import com.sde.assigment.models.FacilitiesAndExclusionsModel
import com.sde.assigment.room.RoomDBManager
import com.sde.assigment.utils.FacilitiesTask
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DataManager @Inject constructor(
    var facilitiesTask: FacilitiesTask,
    var dbManager: RoomDBManager,
    var compositeDisposable: CompositeDisposable
)  {
    fun loadDataFromAPI(apiCallback: ApiCallback) {
        facilitiesTask.getFacilities(object : Callback<FacilitiesAndExclusionsModel> {
            override fun returnError(message: String) {
                apiCallback.onApiError(message)
            }

            override fun returnResult(t: FacilitiesAndExclusionsModel) {
                apiCallback.onApiSuccess(t)
            }
        })
    }

    fun loadDataFromRoomDB(roomDataFetchCallback: RoomDataFetchCallback) {
        val facilitiesObservable = Observable.fromCallable { dbManager.getAllFacilities() }
        val exclusionsObservable = Observable.fromCallable { dbManager.getAllExclusion() }

        val allItemsObservable = Observable.zip(
            facilitiesObservable.subscribeOn(Schedulers.io()),
            exclusionsObservable.subscribeOn(Schedulers.io())
        ) { facilities, exclusions ->
            Pair(facilities, exclusions)
        }.observeOn(Schedulers.computation())
            .subscribe(
                { (facilities, exclusions) ->
                    roomDataFetchCallback.onFetchSuccess(facilities, exclusions)

                },
                { error ->
                    // Handle the error here
//                    mView.showError(error.message.toString())
                    roomDataFetchCallback.onFetchError(error.message.toString())
                    error.printStackTrace()
                }
            )

        compositeDisposable.add(allItemsObservable)
    }

    fun saveLocalData(model: FacilitiesAndExclusionsModel, roomDataSaveCallback: RoomDataSaveCallback) {
        val facilitiesObservable =
            Observable.fromCallable { dbManager.insertAllFacility(model.facilities) }
        val exclusionsObservable = Observable.fromCallable {
            for (exclusionList in model.exclusions) {
                dbManager.insertAllExclusion(exclusionList)
            }
        }
        val saveCacheObservable = Observable.zip(
            facilitiesObservable.subscribeOn(Schedulers.io()),
            exclusionsObservable.subscribeOn(Schedulers.io())
        ) { facilities, exclusions ->
            Pair(facilities, exclusions)
        }.observeOn(Schedulers.computation())
            .subscribe(
                {
                    roomDataSaveCallback.onSaveSuccess()

                },
                { error ->
                    // Handle the error here
                    error.printStackTrace()
                    roomDataSaveCallback.onSaveError(error.message.toString())

                }
            )
        compositeDisposable.add(saveCacheObservable)

    }

    fun clearLocalData(){
        val clearLocalDataObservable =
            Observable.fromCallable { dbManager.clearLocalData() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.e("DataManager","clearLocalData Success")
                }
        compositeDisposable.add(clearLocalDataObservable)
    }

}