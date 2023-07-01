package com.sde.assigment.dagger.modules

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val mActivity: AppCompatActivity) {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}