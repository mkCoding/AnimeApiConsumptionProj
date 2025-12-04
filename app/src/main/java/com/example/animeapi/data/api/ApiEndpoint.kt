package com.example.animeapi.data.api

import com.example.animeapi.data.model.TopAnimeModel
import retrofit2.http.GET

interface ApiEndpoint {

    @GET(ApiDetails.ENDPOINT_ANIME_TOP)
    suspend fun getTopAnime(): TopAnimeModel

}