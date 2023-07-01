package com.sde.assigment.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class FacilitiesAndExclusionsModel(
    @SerializedName("facilities") var facilities: List<Facility> = arrayListOf(),
    @SerializedName("exclusions") var exclusions: List<List<Exclusion>> = arrayListOf()
)

@Entity(tableName="facilities")
class Facility(
    @PrimaryKey(autoGenerate = true)
    var id:Long?=null,

    @SerializedName("facility_id") var facilityId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("options") var options: List<Option> = arrayListOf()
)

data class Option(
    @SerializedName("name") var name: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("id") var id: String? = null
){
    override fun toString(): String {
        return "Option(name=$name, icon=$icon, id=$id)\n"
    }
}

@Entity(tableName="exclusions")
class Exclusion (
    @PrimaryKey(autoGenerate = true)
    var id:Long?=null,

    @SerializedName("facility_id" ) var facilityId : String? = null,
    @SerializedName("options_id"  ) var optionsId  : String? = null

)