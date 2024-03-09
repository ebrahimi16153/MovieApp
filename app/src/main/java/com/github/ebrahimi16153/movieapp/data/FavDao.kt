package com.github.ebrahimi16153.movieapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favMovie: FavMovie)


    @Delete
    suspend fun delete(favMovie: FavMovie)


    @Query("SELECT * FROM tblFav")
     fun favMovieList():MutableList<FavMovie>


    @Query("SELECT EXISTS (SELECT 1 FROM tblFav WHERE id =:movieId)")
    suspend fun existsMovie(movieId:Int):Boolean


}