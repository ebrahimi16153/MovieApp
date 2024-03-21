package com.github.ebrahimi16153.movieapp.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.ebrahimi16153.movieapp.databinding.MovieListItemBinding
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList
import javax.inject.Inject

class MoviesAdapter @Inject constructor() :
    PagingDataAdapter<ResponseOfMovieList.Data, MoviesAdapter.ViewHolder>(diffCallback = differCallback) {

    private lateinit var binding: MovieListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(item = getItem(position)!!)
        holder.setIsRecyclable(false)
    }


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: ResponseOfMovieList.Data) {

            binding.apply {

                moviePoster.load(data = item.poster){
                    crossfade(true)
                    crossfade(500)
                }
                titleName.text = item.title
                country.text = item.country
                publishYear.text = item.year
                rating.text = item.imdbRating


            }
            binding.root.setOnClickListener {

                onItemClickListener?.let {
                    it(item)
                }


            }
        }
    }
    // differ must define in companion object

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<ResponseOfMovieList.Data>() {
            override fun areItemsTheSame(
                oldItem: ResponseOfMovieList.Data,
                newItem: ResponseOfMovieList.Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseOfMovieList.Data,
                newItem: ResponseOfMovieList.Data
            ): Boolean {

                return oldItem == newItem
            }

        }
    }


    // onClickListener
    private var onItemClickListener: ((ResponseOfMovieList.Data) -> Unit)? = null

    fun seOnItemClickListener(listener: (ResponseOfMovieList.Data) -> Unit) {
        onItemClickListener = listener
    }
}
