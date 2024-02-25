package com.github.ebrahimi16153.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.movieapp.models.register.BodyRegister
import com.github.ebrahimi16153.movieapp.models.register.ResponseRegister
import com.github.ebrahimi16153.movieapp.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepository: RegisterRepository):ViewModel() {

     val registerUser = MutableLiveData<ResponseRegister>()
    val loadingState = MutableLiveData<Boolean>()

    fun registerUser(bodyRegister: BodyRegister){

        viewModelScope.launch {
            loadingState.postValue(true)

            val responseOfRegister = registerRepository.registerUser(bodyRegister = bodyRegister)

            if (responseOfRegister.isSuccessful){

                registerUser.postValue(responseOfRegister.body())

            }
            loadingState.postValue(false)

        }

    }




}