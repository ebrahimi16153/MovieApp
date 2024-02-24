package com.github.ebrahimi16153.movieapp.api

import com.github.ebrahimi16153.movieapp.models.register.BodyRegister
import com.github.ebrahimi16153.movieapp.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("register")
    suspend fun registerUser(@Body bodyRegister: BodyRegister):Response<ResponseRegister>

}