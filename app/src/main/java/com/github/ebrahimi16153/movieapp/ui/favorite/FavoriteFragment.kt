package com.github.ebrahimi16153.movieapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ebrahimi16153.movieapp.R
import com.github.ebrahimi16153.movieapp.databinding.FragmentFavoriteBinding
import com.github.ebrahimi16153.movieapp.utils.initRecycler
import com.github.ebrahimi16153.movieapp.utils.setVisibility
import com.github.ebrahimi16153.movieapp.viewmodel.FavViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentFavoriteBinding

    // adapter
    @Inject
    lateinit var favMovieListAdapter: FavMovieListAdapter

    // viewModel
    val viewModel :FavViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            viewModel.getFavList()
            viewModel.favList.observe(viewLifecycleOwner){
                favMovieListAdapter.setData(data = it)
                favMovieListRecycler.initRecycler(layoutManager = LinearLayoutManager(requireContext()), adapter = favMovieListAdapter)
            }

            viewModel.empty.observe(viewLifecycleOwner){
                if (it){
                    favEmptyLay.setVisibility(true)
                    favMovieListRecycler.setVisibility(false)
                }else{
                    favEmptyLay.setVisibility(false)
                    favMovieListRecycler.setVisibility(true)
                }
            }
        }

    }


}