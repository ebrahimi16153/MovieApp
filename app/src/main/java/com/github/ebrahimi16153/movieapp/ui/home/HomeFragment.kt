package com.github.ebrahimi16153.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.github.ebrahimi16153.movieapp.R
import com.github.ebrahimi16153.movieapp.databinding.FragmentHomeBinding
import com.github.ebrahimi16153.movieapp.ui.home.adapters.MainBannerAdapter
import com.github.ebrahimi16153.movieapp.utils.initRecycler
import com.github.ebrahimi16153.movieapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentHomeBinding

//    // adapter
    @Inject
    lateinit var mainBannerAdapter: MainBannerAdapter


    // viewModel
    private val viewModel: HomeViewModel by viewModels()

    //pageHelper
    private val pageHelpers: PagerSnapHelper by lazy { PagerSnapHelper() }


    //this fun just call once when app launched
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cal api for once
        viewModel.getMainBannerMovieList(id = 3)
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

            viewModel.mainBannerMoveList.observe(viewLifecycleOwner) { listOfMovie ->
                // getData
                mainBannerAdapter.differ.submitList(listOfMovie.data)

//                mainBanner.initRecycler(
//                    layoutManager = LinearLayoutManager(
//                        requireContext(),
//                        LinearLayoutManager.HORIZONTAL,
//                        false
//                    ), adapter = mainBannerAdapter
//                )

                mainBanner.adapter = mainBannerAdapter
                mainBanner.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)



                //attachIndicator and pageHelper
                pageHelpers.attachToRecyclerView(mainBanner)
                topBannerIndicator.attachToRecyclerView(mainBanner,pageHelpers)


            }


        }


    }

}