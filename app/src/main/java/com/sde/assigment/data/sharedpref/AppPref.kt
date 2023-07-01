package com.sde.assigment.data.sharedpref

import android.content.Context

class AppPref(appContext: Context): SharedPrefUtil(appContext) {

    var lastUpdated:Long
        set(value) = setLong("lastUpdated",value)
        get() = getLong("lastUpdated")

}