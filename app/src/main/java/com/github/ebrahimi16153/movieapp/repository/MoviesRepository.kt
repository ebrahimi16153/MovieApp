package com.github.ebrahimi16153.movieapp.repository

import com.github.ebrahimi16153.movieapp.api.ApiServices
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun getAllMovie(page:Int): Response<ResponseOfMovieList> = apiServices.getLastMovie(page = page)

}