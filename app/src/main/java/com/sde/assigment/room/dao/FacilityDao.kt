package com.sde.assigment.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sde.assigment.models.Facility

@Dao
interface FacilityDao {
    @Insert
    fun insert(facility: Facility)

    @Insert
    fun insertAll(facilities: List<Facility>)

    @Query("select * from facilities")
    fun getAll(): List<Facility>
}