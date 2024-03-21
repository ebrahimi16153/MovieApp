package com.github.ebrahimi16153.movieapp.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ebrahimi16153.movieapp.databinding.LoadMoreBinding

class LoadMoreAdapter(private val setOnItemClick: () -> Unit) :
    LoadStateAdapter<LoadMoreAdapter.ViewHolder>() {


    private lateinit var binding: LoadMoreBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ViewHolder {
        binding = LoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(setOnItemClick = setOnItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.setData(loadState = loadState)

    }


    inner class ViewHolder(setOnItemClick: () -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.loadRetry.setOnClickListener {
                setOnItemClick()
            }
        }

        fun setData(loadState: LoadState) {
            binding.apply {
                loadMore.isVisible = loadState is LoadState.Loading
                loadError.isVisible = loadState is LoadState.Error
                loadRetry.isVisible = loadState is LoadState.Error
            }
        }
    }


}