package com.sde.assigment.utils

import android.content.Context
import com.sde.assigment.models.FacilitiesAndExclusionsModel
import com.sde.assigment.callback.Callback
import com.sde.assigment.dagger.getInjector
import com.sde.assigment.networking.utils.NetworkingUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FacilitiesTask(val context: Context) {
    @Inject
    lateinit var networkingUtils:NetworkingUtils

    init {
        getInjector(context).inject(this)
    }

    fun getFacilities(callback: Callback<FacilitiesAndExclusionsModel>){
        networkingUtils.createService().
        getFacilitiesAndExclusionsModel()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: Observer<FacilitiesAndExclusionsModel>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    callback.returnError(e.message?:"Error")
                }

                override fun onComplete() {
                }

                override fun onNext(t: FacilitiesAndExclusionsModel) {
                    callback.returnResult(t)
                }

            })
    }
}