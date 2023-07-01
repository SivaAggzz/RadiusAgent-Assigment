package com.sde.assigment.dagger.modules

import android.content.Context
import com.sde.assigment.MyApp
import com.sde.assigment.data.sharedpref.AppPref
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
object ApplicationModule {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return MyApp.instance.applicationContext
    }

    @Singleton
    @Provides
    fun provideAppPref(): AppPref {
        return AppPref(provideApplicationContext())
    }

    @Singleton
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }




}