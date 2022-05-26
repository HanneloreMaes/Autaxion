package com.hanneloremaes.autaxion.models

import retrofit2.Call
import retrofit2.http.GET

interface CarApi {
    @GET("/manufacturers")
    fun fetchAllCars(): Call<List<Car>>
}