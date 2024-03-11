package com.github.ebrahimi16153.movieapp.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.ebrahimi16153.movieapp.databinding.MovieListItemBinding
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList.Data
import javax.inject.Inject

class MovieListAdapter @Inject constructor() :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private lateinit var binding: MovieListItemBinding
    private var movieList: List<Data> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListAdapter.ViewHolder {
        binding =
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.bindViews(item = movieList[position])
        // for handel duplicated item
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = movieList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindViews(item: Data) {
            binding.titleName.text = item.title
            binding.rating.text = item.imdbRating
            binding.country.text =  item.country
            binding.publishYear.text =  item.year
            binding.moviePoster.load(item.poster) {
                crossfade(true)
                crossfade(800)


            }
            binding.root.setOnClickListener {

                onItemClickListener?.let {
                    it(item)
                }

            }
        }

    }


    // a differ class and function for dynamic adapter -> when data of adapter can change many times
    // whe must handel oldItems and new items, in fact we handel how adapter update new items

    fun setData(data:List<Data>){

        val movieListDiffer = MovieListDiffer(oldItems = movieList , newItems = data)
        val diffUtil = DiffUtil.calculateDiff(movieListDiffer)
        movieList = data
        diffUtil.dispatchUpdatesTo(this)

    }




    class MovieListDiffer(private val oldItems: List<Data>, private val newItems: List<Data>) :
        DiffUtil.Callback() {


        override fun getOldListSize() = oldItems.size

        override fun getNewListSize() = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] === newItems[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] === newItems[newItemPosition]

    }


    // onClickListener
    private var onItemClickListener:((Data) -> Unit)? = null

    fun seOnItemClickListener(listener:(Data) -> Unit){
        onItemClickListener = listener
    }


}