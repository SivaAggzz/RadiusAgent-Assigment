package com.sde.assigment.data.interfaces

import com.sde.assigment.models.Exclusion
import com.sde.assigment.models.Facility

interface DBInterface {
    fun getAllFacilities(): List<Facility>

    fun getAllExclusion(): List<Exclusion>

    fun insertAllFacility(facilities: List<Facility>)

    fun insertAllExclusion(exclusions: List<Exclusion>)

    fun clearLocalData()
}