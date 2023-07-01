package com.sde.assigment.networking.utils

import android.content.Context
import com.sde.assigment.dagger.getInjector
import com.sde.assigment.networking.api.ApiService
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkingUtils @Inject constructor(private val retrofit: Retrofit,private val context: Context) {
    init {
        getInjector(context).inject(this)
    }

    fun createService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}