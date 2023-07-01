package com.sde.assigment.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sde.assigment.models.Option

class OptionConverters {
    @TypeConverter
    fun fromOptionList(optionList: List<Option>): String {
        val gson = Gson()
        val type =
            object : TypeToken<List<Option>>() {
            }.type
        return gson.toJson(optionList, type)
    }

    @TypeConverter
    fun toOptionList(optionString: String): List<Option> {
        val gson = Gson()
        val type =
            object : TypeToken<List<Option>>() {
            }.type
        return gson.fromJson(optionString, type)
    }
}