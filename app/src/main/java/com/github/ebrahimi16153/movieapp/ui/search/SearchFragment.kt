package com.github.ebrahimi16153.movieapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.movieapp.databinding.FragmentSearchBinding
import com.github.ebrahimi16153.movieapp.ui.home.adapters.MovieListAdapter
import com.github.ebrahimi16153.movieapp.utils.initRecycler
import com.github.ebrahimi16153.movieapp.utils.setVisibility
import com.github.ebrahimi16153.movieapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {


    //binding
    private lateinit var binding: FragmentSearchBinding

    // viewModel
    private val viewModel:SearchViewModel by viewModels()

    // adapter
    @Inject
    lateinit var movieListAdapter: MovieListAdapter





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init views
        binding.apply {

            edtSearch.addTextChangedListener{
                val searchQuery = it.toString()
                viewModel.getSearchResult(searchQuery = searchQuery)

                 // get Result of search
                viewModel.searchResult.observe(viewLifecycleOwner){ searchResult ->

                    movieListAdapter.setData(searchResult.data)
                    searchMovieListRecycler.initRecycler(layoutManager = LinearLayoutManager(requireContext()), adapter = movieListAdapter)

                }



            }
            movieListAdapter.seOnItemClickListener {data ->
                val directions = SearchFragmentDirections.actionToDetailFragment3(data.id!!)
                findNavController().navigate(directions)
            }
            viewModel.loading.observe(viewLifecycleOwner){ isShow ->
                if (isShow){
                    searchLoading.setVisibility(true)
                    searchMovieListRecycler.setVisibility(false)
                }else{
                    searchLoading.setVisibility(false)
                    searchMovieListRecycler.setVisibility(true)
                }
            }

            viewModel.isEmptyList.observe(viewLifecycleOwner){
                if (it){
                    emptyLay.setVisibility(true)
                    searchMovieListRecycler.setVisibility(false)
                }else{
                    emptyLay.setVisibility(false)
                    searchMovieListRecycler.setVisibility(true)
                }


            }


        }


    }

}