package com.sde.assigment.dagger

import android.content.Context
import com.sde.assigment.dagger.components.AppComponent

interface InjectorProvider {
  val component: AppComponent
}

fun getInjector(context: Context) = (context.applicationContext as InjectorProvider).component
