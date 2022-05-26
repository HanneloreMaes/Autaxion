package com.hanneloremaes.autaxion.models.model

import retrofit2.Call
import retrofit2.http.GET

interface ModelApi {
    @GET("/manufacturers")
    fun fetchAllCars(): Call<List<Model>>
}