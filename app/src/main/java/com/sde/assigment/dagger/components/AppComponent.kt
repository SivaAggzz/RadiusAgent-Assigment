package com.sde.assigment.dagger.components

import android.content.Context
import com.sde.assigment.MyApp
import com.sde.assigment.dagger.modules.ApplicationModule
import com.sde.assigment.dagger.modules.NetworkModule
import com.sde.assigment.dagger.modules.RoomModule
import com.sde.assigment.home.HomePresenter
import com.sde.assigment.networking.utils.NetworkingUtils
import com.sde.assigment.utils.FacilitiesTask
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules =
    [
      ApplicationModule::class,
      NetworkModule::class,
      RoomModule::class
    ]
)
interface AppComponent {
  fun inject(myApp: MyApp)
  fun inject(networkingUtils: NetworkingUtils)
  fun inject(facilitiesTask: FacilitiesTask)
  fun inject(homePresenter: HomePresenter)

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance applicationContext: Context): AppComponent
  }
}
