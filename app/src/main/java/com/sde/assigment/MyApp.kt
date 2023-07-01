package com.sde.assigment

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.sde.assigment.dagger.InjectorProvider
import com.sde.assigment.dagger.components.AppComponent
import com.sde.assigment.dagger.components.DaggerAppComponent
import com.sde.assigment.dagger.getInjector

class MyApp : Application(), InjectorProvider {

    override fun onCreate() {
        super.onCreate()
        getInjector(this).inject(this)
        instance=this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
    override val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    companion object{
        lateinit var instance:MyApp
    }
}