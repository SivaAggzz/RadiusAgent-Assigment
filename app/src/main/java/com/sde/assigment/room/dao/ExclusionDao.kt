package com.sde.assigment.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sde.assigment.models.Exclusion
import com.sde.assigment.models.Facility
import io.reactivex.Single

@Dao
interface ExclusionDao {
    @Insert
    fun insert(exclusion: Exclusion)
    @Insert
    fun insertAll(exclusions: List<Exclusion>)

    @Query("select * from exclusions")
    fun getAll():List<Exclusion>
}