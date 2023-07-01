package com.sde.assigment.models

data class DataModel(
    var propertyType: PropertyType,
    var roomOptions: ArrayList<Room>,
    var otherFacilitiesOptions: ArrayList<OtherFacilities>,
)


data class PropertyType(
    var type:String,
    var icon:String
)

data class Room(
    var type:String,
    var icon:String
)

data class OtherFacilities(
    var type:String,
    var icon:String
)