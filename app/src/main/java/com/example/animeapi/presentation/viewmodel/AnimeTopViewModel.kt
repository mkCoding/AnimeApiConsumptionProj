package com.example.animeapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapi.data.model.TopAnimeModel
import com.example.animeapi.repository.AnimeTopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeTopViewModel(): ViewModel(){
    // create repo to be used to retrieve data from api response
    val repository = AnimeTopRepository()

    // create  variable to hold data to be passed to ui
    private val _animeTopData = MutableStateFlow<AnimeTopState>(AnimeTopState.Loading)
    val animeTopData: StateFlow<AnimeTopState> = _animeTopData


    init {
        loadAnimeTopData()
    }

    // create method to load data

    private fun loadAnimeTopData() {
        viewModelScope.launch {
            _animeTopData.value= AnimeTopState.Loading
            try {
                val animeTopRealData = repository.getTopAnime()
                _animeTopData.value = AnimeTopState.Success(animeTopData = animeTopRealData)

            }catch (e:Exception){
                _animeTopData.value = AnimeTopState.Error("${e.message}")
            }
        }
    }


}

// sealed class for api Success, Loading, Error state
sealed class AnimeTopState{
    object Loading: AnimeTopState()
    data class Success(val animeTopData: TopAnimeModel): AnimeTopState()
    data class Error(val message:String): AnimeTopState()
}