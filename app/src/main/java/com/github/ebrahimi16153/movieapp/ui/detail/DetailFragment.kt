package com.github.ebrahimi16153.movieapp.ui.detail

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.github.ebrahimi16153.movieapp.R
import com.github.ebrahimi16153.movieapp.data.FavMovie
import com.github.ebrahimi16153.movieapp.databinding.FragmentDetailBinding
import com.github.ebrahimi16153.movieapp.utils.initRecycler
import com.github.ebrahimi16153.movieapp.utils.setVisibility
import com.github.ebrahimi16153.movieapp.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    // binding
    private lateinit var binding: FragmentDetailBinding

    // Actors Image Adapter
    @Inject
    lateinit var imageAdapter: DetailImageAdapter

    // get nav args
    private val args: DetailFragmentArgs by navArgs()

    // movieID
    private var movieId = 0

    //viewModel
    private val viewModel: DetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // get movieId
        movieId = args.movieID
        if (movieId > 0) {
            //get movieDetails
            viewModel.getDetailMovie(movieId = movieId)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {


            viewModel.detailMovie.observe(viewLifecycleOwner) { response ->

                backgroundPoster.load(response.poster)
                mainPoster.load(response.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                detailTitle.text = response.title
                rate.text = response.imdbRating
                time.text = response.runtime
                year.text = response.year
                description.text = response.plot
                actorsName.text = response.actors

                imageAdapter.differ.submitList(response.images)
                actorsImage.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    imageAdapter
                )

                viewModel.loading.observe(viewLifecycleOwner) { show ->
                    if (show) {
                        detailLoading.setVisibility(true)
                        detailContent.setVisibility(false)

                    } else {
                        detailLoading.setVisibility(false)
                        detailContent.setVisibility(true)
                    }
                }

                //            favButton
                viewModel.isExists(response.id!!)



                //back button
                backButton.setOnClickListener {
                    findNavController().navigateUp()
                }

            }

            viewModel.isExists.observe(viewLifecycleOwner) {
                if (it) {
                    favButton.setColorFilter(resources.getColor(R.color.scarlet, null))
                } else {
                    favButton.setColorFilter(resources.getColor(R.color.philippineSilver, null))


                }

        }
    }
}