package com.sde.assigment.callback

interface Callback<T> {
    fun returnResult(t: T)
    fun returnError(message:String)
}