package com.sde.assigment.data.interfaces

import com.sde.assigment.models.Exclusion
import com.sde.assigment.models.Facility

interface RoomDataFetchCallback {
    fun onFetchSuccess(facilities:List<Facility>?, exclusions: List<Exclusion>?)
    fun onFetchError(message: String)
}