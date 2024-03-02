package com.github.ebrahimi16153.movieapp.repository

import com.github.ebrahimi16153.movieapp.api.ApiServices
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: ApiServices) {

    suspend fun searchQuery(searchQuery: String): Response<ResponseOfMovieList> =
        api.searchQuery(searchQuery)


}