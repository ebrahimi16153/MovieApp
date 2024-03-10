package com.github.ebrahimi16153.movieapp.api

import com.github.ebrahimi16153.movieapp.models.ResponseOfDetail
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfGenresList
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList
import com.github.ebrahimi16153.movieapp.models.register.BodyRegister
import com.github.ebrahimi16153.movieapp.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {


    //registration
    @POST("register")
    suspend fun registerUser(@Body bodyRegister: BodyRegister): Response<ResponseRegister>

    // Home Main Banner MovieList
    @GET("genres/{genre_id}/movies")
    suspend fun getMainBannerMovieList(@Path("genre_id") id: Int): Response<ResponseOfMovieList>

    // list of genres
    @GET("genres")
    suspend fun getGenres():Response<ResponseOfGenresList>

    // list of last movie
    @GET("movies")
    suspend fun getLastMovie():Response<ResponseOfMovieList>

    //Search
    @GET("movies")
    suspend fun searchQuery(@Query("q") searchQuery:String):Response<ResponseOfMovieList>

    //getMovieDetail
    @GET("movies/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId:Int):Response<ResponseOfDetail>


}