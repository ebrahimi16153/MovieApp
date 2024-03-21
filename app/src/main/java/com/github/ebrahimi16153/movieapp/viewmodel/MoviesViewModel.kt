package com.github.ebrahimi16153.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.github.ebrahimi16153.movieapp.paging.MoviesPaging
import com.github.ebrahimi16153.movieapp.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository):ViewModel() {

    // get LastMovieList
    val allMovieList = Pager(PagingConfig(1)){
        MoviesPaging( repository = moviesRepository)
    }.flow.cachedIn(viewModelScope)
}