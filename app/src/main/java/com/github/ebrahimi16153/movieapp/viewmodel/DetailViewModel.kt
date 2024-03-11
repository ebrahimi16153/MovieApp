package com.github.ebrahimi16153.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.movieapp.data.FavMovie
import com.github.ebrahimi16153.movieapp.models.ResponseOfDetail
import com.github.ebrahimi16153.movieapp.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepository: DetailRepository) :
    ViewModel() {

    val detailMovie = MutableLiveData<ResponseOfDetail>()
    val loading = MutableLiveData<Boolean>()




    fun getDetailMovie(movieId: Int) = viewModelScope.launch {
        loading.postValue(true)
        val response = detailRepository.getDetailMovie(movieId)
        if (response.isSuccessful) {
            detailMovie.postValue(response.body())

        }
        loading.postValue(false)

    }


    // dataBase
   // my way
//    val isExists = MutableLiveData<Boolean>()
//    fun insertFav(favMovie: FavMovie) = viewModelScope.launch {
//        detailRepository.insert(favMovie)
//    }
//
//    fun isExists(movieId: Int) = viewModelScope.launch {
//        val exists = detailRepository.isExists(movieId)
//
//        if (exists) isExists.postValue(true) else isExists.postValue(false)
//    }
//
//    fun deleteFav(favMovie: FavMovie) = viewModelScope.launch {
//        detailRepository.delete(favMovie)
//    }


    // other way

    val isFavorite = MutableLiveData<Boolean>()
    suspend fun  existsMovie(movieId:Int) = withContext(viewModelScope.coroutineContext){   detailRepository.isExists(movieId)  }


    //for onclickListener favButton
    fun favoriteMovie(movieId:Int , favMovie: FavMovie) = viewModelScope.launch {

        val exists = detailRepository.isExists(movieId)
        if (exists){
            isFavorite.postValue(false)
            detailRepository.delete(favMovie)
        }else{
            isFavorite.postValue(true)
            detailRepository.insert(favMovie)
        }

    }


}