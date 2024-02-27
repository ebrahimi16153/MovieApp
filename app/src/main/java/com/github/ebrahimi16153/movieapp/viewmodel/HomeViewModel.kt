package com.github.ebrahimi16153.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList
import com.github.ebrahimi16153.movieapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    val mainBannerMoveList = MutableLiveData<ResponseOfMovieList>()

    fun getMainBannerMovieList(id: Int) {
        viewModelScope.launch {

            val response = homeRepository.getMainBannerContent(id = id)

            if (response.isSuccessful) {

                mainBannerMoveList.postValue(response.body())

            }

        }


    }


}