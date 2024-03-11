package com.github.ebrahimi16153.movieapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.ebrahimi16153.movieapp.databinding.ActorsImageItemBinding
import javax.inject.Inject

class DetailImageAdapter @Inject constructor() :
    RecyclerView.Adapter<DetailImageAdapter.ViewHolder>() {

    private lateinit var binding: ActorsImageItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailImageAdapter.ViewHolder {
        binding = ActorsImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: DetailImageAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun setData(item:String){
            binding.actorImageItem.load(item){
                crossfade(true)
                crossfade(800)
            }

        }


    }


    private val defferCallBAck = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, defferCallBAck)


}