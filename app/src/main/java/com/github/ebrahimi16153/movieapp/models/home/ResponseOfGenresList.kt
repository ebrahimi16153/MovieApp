package com.github.ebrahimi16153.movieapp.models.home


import com.google.gson.annotations.SerializedName

class ResponseOfGenresList : ArrayList<ResponseOfGenresList.ResponseOfGenresListItem>(){
    data class ResponseOfGenresListItem(
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("name")
        val name: String? // Crime
    )
}