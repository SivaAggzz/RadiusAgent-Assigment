package com.sde.assigment.data.sharedpref

import android.content.Context

open class SharedPrefUtil(val context: Context) {
    private val sharedPreferences=context.getSharedPreferences("AppPref", Context.MODE_PRIVATE)
    fun setLong(key:String,value:Long){
        sharedPreferences.edit().putLong(key,value).apply()
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key,-1L)
    }
}