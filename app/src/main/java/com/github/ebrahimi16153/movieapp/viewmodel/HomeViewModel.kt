package com.github.ebrahimi16153.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfGenresList
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList
import com.github.ebrahimi16153.movieapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    val mainBannerMoveList = MutableLiveData<ResponseOfMovieList>()
    val genres = MutableLiveData<ResponseOfGenresList>()
    val lastMovieList = MutableLiveData<ResponseOfMovieList>()
    val loadingState = MutableLiveData<Boolean>()


    // get MainBanner Movie List
    fun getMainBannerMovieList(id: Int) {
        viewModelScope.launch {
            loadingState.postValue(true)

            val response = homeRepository.getMainBannerContent(id = id)

            if (response.isSuccessful) {

                mainBannerMoveList.postValue(response.body())

            }
            loadingState.postValue(false)
        }


    }

    // get genres List
    fun genresList() {
        viewModelScope.launch {

            val response = homeRepository.getGenres()
            if (response.isSuccessful) {
                genres.postValue(response.body())
            }

        }
    }





//    without paging

    fun latMovieList(page :Int) {
        viewModelScope.launch {
            loadingState.postValue(true)
            val response = homeRepository.getLastMovie(page)
            if (response.isSuccessful) {

                lastMovieList.postValue(response.body())

            }
            loadingState.postValue(false)
        }
    }


}