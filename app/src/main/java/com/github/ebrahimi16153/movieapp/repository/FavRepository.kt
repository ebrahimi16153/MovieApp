package com.github.ebrahimi16153.movieapp.repository

import com.github.ebrahimi16153.movieapp.data.FavDao
import javax.inject.Inject

class FavRepository @Inject constructor(private val favDao: FavDao) {

    fun getFavList() = favDao.favMovieList()

}