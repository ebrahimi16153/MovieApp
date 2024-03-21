package com.github.ebrahimi16153.movieapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList
import com.github.ebrahimi16153.movieapp.repository.MoviesRepository
import javax.inject.Inject

class MoviesPaging @Inject constructor(private val repository: MoviesRepository) :
    PagingSource<Int, ResponseOfMovieList.Data>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseOfMovieList.Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseOfMovieList.Data> {
        return try {

            val currentPage = params.key ?: 1
            val response = repository.getAllMovie(page = currentPage)

            //if api fail
            val data = response.body()?.data ?: emptyList()
            val responseData = mutableListOf<ResponseOfMovieList.Data>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else currentPage.minus(1),
                nextKey = currentPage.plus(1)
            )


        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}