package com.sde.assigment.dagger.modules

import android.content.Context
import com.sde.assigment.MyApp
import com.sde.assigment.models.Exclusion
import com.sde.assigment.models.Facility
import com.sde.assigment.room.AppDatabase
import dagger.Module
import dagger.Provides
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton


@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return MyApp.instance.applicationContext
    }

    @Provides
    @Singleton
    fun providesAppDatabase(): AppDatabase {
        return AppDatabase.getInstance(provideAppContext())
    }

//    @Provides
//    @Singleton
//    fun providesRealm(): Realm {
//        val config = RealmConfiguration.create(schema = setOf(Facility::class, Exclusion::class))
//        return Realm.open(config)
//    }


}