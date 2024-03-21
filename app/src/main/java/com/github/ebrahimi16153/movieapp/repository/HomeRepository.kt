package com.github.ebrahimi16153.movieapp.repository

import com.github.ebrahimi16153.movieapp.api.ApiServices
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfGenresList
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiServices) {

    suspend fun getMainBannerContent(id: Int): Response<ResponseOfMovieList> =
        api.getMainBannerMovieList(id = id)

    suspend fun getGenres():Response<ResponseOfGenresList> = api.getGenres()


    suspend fun getLastMovie(page:Int):Response<ResponseOfMovieList> = api.getLastMovie(page = page)


}