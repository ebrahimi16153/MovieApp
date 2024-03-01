package com.github.ebrahimi16153.movieapp.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.ebrahimi16153.movieapp.databinding.MainbannerHomeItemsBinding
import com.github.ebrahimi16153.movieapp.models.home.ResponseOfMovieList.Data
import javax.inject.Inject

class MainBannerAdapter @Inject constructor() :
    RecyclerView.Adapter<MainBannerAdapter.ViewHolder>() {

    private lateinit var binding: MainbannerHomeItemsBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainBannerAdapter.ViewHolder {
        binding =
            MainbannerHomeItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: MainBannerAdapter.ViewHolder, position: Int) {
        holder.setData(item = differ.currentList[position])
        // for handel duplicated item
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: Data) {
            binding.MainBannerName.text = item.title
            binding.MainBannerInfo.text = "${item.imdbRating} | ${item.country} | ${item.year}"
            binding.mainBannerImage.load(item.poster) {
                crossfade(true)
                crossfade(800)
            }
        }

    }


    private val defferCallBAck = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, defferCallBAck)

}