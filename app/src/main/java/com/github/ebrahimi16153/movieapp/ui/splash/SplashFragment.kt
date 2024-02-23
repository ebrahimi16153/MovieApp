package com.github.ebrahimi16153.movieapp.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.github.ebrahimi16153.movieapp.R
import com.github.ebrahimi16153.movieapp.databinding.FragmentSplashBinding
import com.github.ebrahimi16153.movieapp.utils.UserTokenDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentSplashBinding

    // userTokenDataStore
    @Inject
    lateinit var userTokenDataStore: UserTokenDataStore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // delay
        lifecycle.coroutineScope.launch {

            delay(2000)
            // if userToken exist -> home else -> register
            userTokenDataStore.userToken.collect { token ->

                if (token.isEmpty())
                    findNavController().navigate(R.id.actionSpalshToRegister)
                else
                    findNavController().navigate(R.id.actionSplashToHome
                )
            }
        }
    }
}