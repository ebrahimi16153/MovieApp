package com.github.ebrahimi16153.movieapp.repository

import com.github.ebrahimi16153.movieapp.api.ApiServices
import com.github.ebrahimi16153.movieapp.models.register.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val apiServices: ApiServices) {

    // register user

    suspend fun registerUser(bodyRegister: BodyRegister) =
        apiServices.registerUser(bodyRegister = bodyRegister)


}