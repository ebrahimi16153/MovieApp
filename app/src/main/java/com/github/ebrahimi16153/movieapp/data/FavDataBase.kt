package com.github.ebrahimi16153.movieapp.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database([FavMovie::class], version = 1, exportSchema = false)
abstract class FavDataBase : RoomDatabase() {

  abstract val movieDao :FavDao

}