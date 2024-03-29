package com.github.ebrahimi16153.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.github.ebrahimi16153.movieapp.databinding.FragmentHomeBinding
import com.github.ebrahimi16153.movieapp.ui.home.adapters.GenresAdapter
import com.github.ebrahimi16153.movieapp.ui.home.adapters.MainBannerAdapter
import com.github.ebrahimi16153.movieapp.ui.search.adapter.MovieListAdapter
import com.github.ebrahimi16153.movieapp.utils.initRecycler
import com.github.ebrahimi16153.movieapp.utils.setVisibility
import com.github.ebrahimi16153.movieapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentHomeBinding

    // adapters
    @Inject
    lateinit var mainBannerAdapter: MainBannerAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var lastMovieListAdapter: MovieListAdapter


    // viewModel
    private val viewModel: HomeViewModel by viewModels()

    //pageHelper
    private val pageHelpers: PagerSnapHelper by lazy { PagerSnapHelper() }





    //this fun just call once when app launched
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //call api for once
        viewModel.getMainBannerMovieList(id = 3)
        viewModel.genresList()
        viewModel.latMovieList(2)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            // getData MainBanner

            viewModel.mainBannerMoveList.observe(viewLifecycleOwner) { listOfMovie ->

                mainBannerAdapter.differ.submitList(listOfMovie.data)

                // initRecycler is Extension function
                mainBanner.initRecycler(
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    ), adapter = mainBannerAdapter
                )

                pageHelpers.attachToRecyclerView(mainBanner)
                topBannerIndicator.attachToRecyclerView(mainBanner, pageHelpers)


            }
            // loading
            viewModel.loadingState.observe(viewLifecycleOwner) {

                if (it) {
                    homeLoading.setVisibility(true)
                    nestedScroll.setVisibility(false)

                } else {

                    homeLoading.setVisibility(false)
                    nestedScroll.setVisibility(true)
                }


            }


            //getData Genres
            viewModel.genres.observe(viewLifecycleOwner) {

                genresAdapter.differ.submitList(it)
                genresRecyclerView.initRecycler(
                    layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    ), adapter = genresAdapter
                )



            }


            viewModel.lastMovieList.observe(viewLifecycleOwner){

                lastMovieListAdapter.setData(it.data)

                lastMovieRecyclerView.initRecycler(layoutManager = LinearLayoutManager(requireContext()), adapter = lastMovieListAdapter)

                // swipe to refresh
                swipeToRefresh.setOnRefreshListener {
                    swipeToRefresh.isRefreshing = false
                    lastMovieListAdapter.setData(it.data)
                }


            }

            //load LastMovieList by Paging




            // swipe to refresh


        }


    }

}