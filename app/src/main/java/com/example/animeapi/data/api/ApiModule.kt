package com.example.animeapi.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiModule {

    // Create retrofit instance and link it to endpoint
    val retrofit = Retrofit.Builder()
        .baseUrl(ApiDetails.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api by lazy {   retrofit.create(ApiEndpoint::class.java)}

}