package com.sde.assigment.realmdb

import com.sde.assigment.data.interfaces.DBInterface
import com.sde.assigment.models.Exclusion
import com.sde.assigment.models.Facility
import io.realm.kotlin.Realm
import io.realm.kotlin.query.RealmResults
import javax.inject.Inject

/*
class RealmDbManager @Inject constructor(private val realm: Realm): DBInterface {
    override fun getAllFacilities(): List<Facility> {
        return realm.query(Facility::class).find()
    }

    override fun getAllExclusion(): List<Exclusion> {
        return realm.query(Exclusion::class).find()
    }

    override fun insertAllFacility(facilities: List<Facility>) {
        realm.writeBlocking {
            for (facility in facilities){
                copyToRealm(facility)
            }
        }
    }

    override fun insertAllExclusion(exclusions: List<Exclusion>) {
        realm.writeBlocking {
            for (exclusion in exclusions){
                copyToRealm(exclusion)
            }
        }
    }

    override fun clearLocalData() {
        realm.writeBlocking {
            val facilities: RealmResults<Facility> = this.query(Facility::class).find()
            val exclusions: RealmResults<Exclusion> = this.query(Exclusion::class).find()
            delete(facilities)
            delete(exclusions)
        }
    }

}*/
