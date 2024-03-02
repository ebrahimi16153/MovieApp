package com.github.ebrahimi16153.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList
import com.github.ebrahimi16153.movieapp.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {


    val searchResult = MutableLiveData<ResponseOfMovieList>()
    val loading = MutableLiveData<Boolean>()
    val isEmptyList = MutableLiveData<Boolean>()


    fun getSearchResult(searchQuery: String) {
        loading.postValue(true)

        viewModelScope.launch {

            val response = searchRepository.searchQuery(searchQuery = searchQuery)
            if (response.isSuccessful) {

                if (response.body()!!.data.isNotEmpty())
                    isEmptyList.postValue(false)
                else
                    isEmptyList.postValue(true)

                searchResult.postValue(response.body())
            }
        }
        loading.postValue(false)
    }
}