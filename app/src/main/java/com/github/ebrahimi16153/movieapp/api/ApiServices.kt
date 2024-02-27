package com.github.ebrahimi16153.movieapp.api

import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList
import com.github.ebrahimi16153.movieapp.models.register.BodyRegister
import com.github.ebrahimi16153.movieapp.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {


    //registration
    @POST("register")
    suspend fun registerUser(@Body bodyRegister: BodyRegister): Response<ResponseRegister>

    // Home Main Banner MovieList
    @GET("genres/{genre_id}/movies")
    suspend fun getMainBannerMovieList(@Path("genre_id") id: Int): Response<ResponseOfMovieList>


}