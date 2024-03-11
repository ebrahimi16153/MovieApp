package com.github.ebrahimi16153.movieapp.repository

import com.github.ebrahimi16153.movieapp.api.ApiServices
import com.github.ebrahimi16153.movieapp.data.FavDao
import com.github.ebrahimi16153.movieapp.data.FavMovie
import javax.inject.Inject

class DetailRepository @Inject constructor(private val apiServices: ApiServices ,private val favDao: FavDao) {

   //api
   suspend fun getDetailMovie(movieId:Int) = apiServices.getMovieDetail(movieId = movieId)
   //dataBase
   suspend fun insert(favMovie: FavMovie) = favDao.insert(favMovie)

   suspend fun delete(favMovie: FavMovie) = favDao.delete(favMovie)

   suspend fun isExists(movieId: Int) = favDao.existsMovie(movieId)

}