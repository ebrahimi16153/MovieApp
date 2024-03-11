package com.github.ebrahimi16153.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.launch
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


    @Inject
    lateinit var movie: FavMovie
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

                movie = FavMovie(
                    id = response.id!!,
                    poster = response.poster!!,
                    title = response.title!!,
                    rate = response.imdbRating!!,
                    country = response.country!!,
                    year = response.year!!
                )

                // other Way
                favButton.setOnClickListener {

                    viewModel.favoriteMovie(movieId, movie)


                }

                //               my way
//                viewModel.isExists(response.id)
//                viewModel.isExists.observe(viewLifecycleOwner){isExists ->
//
//                    if (isExists){
//                        favButton.setColorFilter(resources.getColor(R.color.scarlet, null))
//                    }else{
//                        favButton.setColorFilter(resources.getColor(R.color.philippineSilver, null))
//                    }
//
//                    favButton.setOnClickListener {
//                        if (isExists){
//
//                            viewModel.deleteFav(movie)
//                            favButton.setColorFilter(resources.getColor(R.color.philippineSilver, null))
//
//
//                        }else{
//                            viewModel.insertFav(movie)
//                            favButton.setColorFilter(resources.getColor(R.color.scarlet, null))
//                        }
//                    }
//                }

            }
            backButton.setOnClickListener {
                findNavController().navigateUp()
            }

            //other way
//            default fav icon color
            lifecycleScope.launch {
                if (viewModel.existsMovie(movieId)) {
                    favButton.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.scarlet
                        )
                    )
                } else {
                    favButton.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.philippineSilver
                        )
                    )
                }
            }

            // change tint of favButton after click
            viewModel.isFavorite.observe(viewLifecycleOwner) {
                if (it) {
                    favButton.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.scarlet
                        )
                    )
                } else {
                    favButton.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.philippineSilver
                        )
                    )
                }


            }

        }
    }
}