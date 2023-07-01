package com.sde.assigment.dagger.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sde.assigment.BuildConfig
import com.sde.assigment.MyApp
import com.sde.assigment.networking.utils.NetworkingUtils
import com.sde.assigment.utils.FacilitiesTask
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideContext(): Context {
        return MyApp.instance.applicationContext
    }
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(4, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkingUtils(): NetworkingUtils {
        return NetworkingUtils(provideRetrofit(), provideContext())
    }

    @Singleton
    @Provides
    fun provideFacilitiesTask(): FacilitiesTask {
        return FacilitiesTask(provideContext())
    }

}