package com.sde.assigment.dagger

import android.content.ContentProvider
import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.sde.assigment.dagger.components.AppComponent

val ContentProvider.injector
  get() = (context?.applicationContext as InjectorProvider).component
val FragmentActivity.injector
  get() = (application as InjectorProvider).component

interface InjectorProvider {
  val component: AppComponent
}

fun getInjector(context: Context) = (context.applicationContext as InjectorProvider).component
