package com.sde.assigment.data.interfaces

import com.sde.assigment.models.FacilitiesAndExclusionsModel

interface ApiCallback {
    fun onApiSuccess(t: FacilitiesAndExclusionsModel)
    fun onApiError(message: String)
}