package com.github.ebrahimi16153.movieapp.repository

import com.github.ebrahimi16153.movieapp.api.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val apiServices: ApiServices) {

   suspend fun getDetailMovie(movieId:Int) = apiServices.getMovieDetail(movieId = movieId)

}