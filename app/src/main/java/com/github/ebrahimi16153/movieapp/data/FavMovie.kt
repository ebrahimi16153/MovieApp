package com.github.ebrahimi16153.movieapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("tblFav")
data class FavMovie(
    @PrimaryKey
    var id :Int = 0,
    var poster:String ="",
    var title:String ="",
    var rate:String ="",
    var country:String ="",
    var year:String =""
)