package com.example.animeapi.repository

import com.example.animeapi.data.api.ApiModule

class AnimeTopRepository {

    // initialize api used to pull endpoint
    val api = ApiModule.api
    suspend fun getTopAnime() = api.getTopAnime()
}