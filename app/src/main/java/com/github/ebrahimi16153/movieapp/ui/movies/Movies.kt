package com.github.ebrahimi16153.movieapp.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.movieapp.databinding.FragmentMoviesBinding
import com.github.ebrahimi16153.movieapp.ui.movies.adapter.LoadMoreAdapter
import com.github.ebrahimi16153.movieapp.ui.movies.adapter.MoviesAdapter
import com.github.ebrahimi16153.movieapp.utils.initRecycler
import com.github.ebrahimi16153.movieapp.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class Movies : Fragment() {

    // binding
    private lateinit var binding: FragmentMoviesBinding

    //viewModel
    private val viewModel: MoviesViewModel by viewModels()

    // adapter

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            // getData
            lifecycleScope.launch {

                viewModel.allMovieList.collect {

                    moviesAdapter.submitData(it)

                }
            }
            // loading
            lifecycleScope.launch {
                moviesAdapter.loadStateFlow.collect{

                    moviesLoading.isVisible = it.refresh is LoadState.Loading
                }
            }

            //recycler
            moviesRecyclerView.initRecycler(
                layoutManager = LinearLayoutManager(requireContext()),
                adapter = moviesAdapter.withLoadStateFooter(
                    LoadMoreAdapter(setOnItemClick = {
                        moviesAdapter.retry()
                    })
                )
            )

            // setOnClick
            moviesAdapter.seOnItemClickListener {
                val directions = MoviesDirections.actionToDetailFragment3(it.id!!)

                findNavController().navigate(directions)

            }
            // swipe to refresh
            moviesRefresh.setOnRefreshListener {
                moviesRefresh.isRefreshing = false
                moviesAdapter.refresh()
            }

        }
    }

}