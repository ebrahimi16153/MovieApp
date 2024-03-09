package com.github.ebrahimi16153.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.movieapp.data.FavMovie
import com.github.ebrahimi16153.movieapp.repository.FavRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavViewModel @Inject constructor(private val favRepository: FavRepository) : ViewModel() {

    val favList = MutableLiveData<MutableList<FavMovie>>()
    val empty = MutableLiveData<Boolean>()


    fun getFavList() = viewModelScope.launch {
        val list = favRepository.getFavList()
        if (list.isEmpty()) {
            empty.postValue(true)
        } else {
            favList.postValue(list)
            empty.postValue(false)
        }
    }


}