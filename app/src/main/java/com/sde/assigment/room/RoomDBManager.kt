package com.sde.assigment.room

import com.sde.assigment.data.interfaces.DBInterface
import com.sde.assigment.models.Exclusion
import com.sde.assigment.models.Facility
import javax.inject.Inject

class RoomDBManager @Inject constructor(private val appDatabase: AppDatabase): DBInterface {
    override fun getAllFacilities(): List<Facility> {
        return appDatabase.facilityDao().getAll()
    }

    override fun getAllExclusion(): List<Exclusion> {
        return appDatabase.exclusionDao().getAll()
    }

    override fun insertAllFacility(facilities: List<Facility>) {
        appDatabase.facilityDao().insertAll(facilities)
    }

    override fun insertAllExclusion(exclusions: List<Exclusion>) {
        appDatabase.exclusionDao().insertAll(exclusions)
    }

    override fun clearLocalData() {
        appDatabase.destroyInstance()
    }
}