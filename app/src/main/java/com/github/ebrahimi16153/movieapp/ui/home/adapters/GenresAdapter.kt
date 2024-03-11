package com.github.ebrahimi16153.movieapp.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ebrahimi16153.movieapp.databinding.GenresItemBinding
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfGenresList.ResponseOfGenresListItem
import javax.inject.Inject

class GenresAdapter @Inject constructor() : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    private lateinit var binding: GenresItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresAdapter.ViewHolder {
        binding = GenresItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: GenresAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun setData(item: ResponseOfGenresListItem) {

            binding.genresItem.text = item.name

        }


    }

    private val differUtil =
        object : DiffUtil.ItemCallback<ResponseOfGenresListItem>() {
            override fun areItemsTheSame(
                oldItem: ResponseOfGenresListItem,
                newItem: ResponseOfGenresListItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseOfGenresListItem,
                newItem: ResponseOfGenresListItem
            ): Boolean {

                return oldItem == newItem
            }

        }


    val differ = AsyncListDiffer(this, differUtil)


}