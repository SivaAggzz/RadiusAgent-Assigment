package com.sde.assigment.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sde.assigment.models.Exclusion
import com.sde.assigment.models.Facility
import com.sde.assigment.room.converters.OptionConverters
import com.sde.assigment.room.dao.ExclusionDao
import com.sde.assigment.room.dao.FacilityDao

@Database(
    entities = [Facility::class, Exclusion::class],
    version = 2
)
@TypeConverters(OptionConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun exclusionDao(): ExclusionDao
    abstract fun facilityDao(): FacilityDao

    fun destroyInstance() {
        try {
            appDatabase?.let { appDatabase!!.clearAllTables() }
            appDatabase = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private var appDatabase: AppDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            appDatabase
                ?: run {
                    appDatabase =
                        Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "MyAppDatabase.db"
                        ).build()
                }
            return appDatabase!!
        }
    }

}