package com.sde.assigment.networking.api

import com.sde.assigment.models.FacilitiesAndExclusionsModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("iranjith4/ad-assignment/db")
    fun getFacilitiesAndExclusionsModel(): Observable<FacilitiesAndExclusionsModel>
}