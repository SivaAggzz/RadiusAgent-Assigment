package com.sde.assigment.data.interfaces

interface RoomDataSaveCallback {
    fun onSaveSuccess()
    fun onSaveError(message: String)
}