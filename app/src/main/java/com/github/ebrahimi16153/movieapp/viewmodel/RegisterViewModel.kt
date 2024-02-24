package com.github.ebrahimi16153.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.github.ebrahimi16153.movieapp.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepository: RegisterRepository):ViewModel() {



}