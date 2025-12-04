package com.example.animeapi.data.model

data class TopAnimeModel(
    val `data`: List<DataModel>? = listOf(),
    val pagination: PaginationModel? = PaginationModel()
)