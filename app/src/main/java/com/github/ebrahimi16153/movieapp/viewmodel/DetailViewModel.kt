package com.github.ebrahimi16153.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.movieapp.models.ResponseOfDetail
import com.github.ebrahimi16153.movieapp.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val  detailRepository: DetailRepository):ViewModel() {

    val detailMovie = MutableLiveData<ResponseOfDetail>()
    val loading = MutableLiveData<Boolean>()


    fun getDetailMovie(movieId:Int) = viewModelScope.launch{
        loading.postValue(true)
        val response =  detailRepository.getDetailMovie(movieId)
        if (response.isSuccessful){
            detailMovie.postValue(response.body())

        }
        loading.postValue(false)

    }


}