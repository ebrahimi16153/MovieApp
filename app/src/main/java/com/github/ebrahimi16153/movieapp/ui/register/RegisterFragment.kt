package com.github.ebrahimi16153.movieapp.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ebrahimi16153.movieapp.R
import com.github.ebrahimi16153.movieapp.databinding.FragmentRegisterBinding
import com.github.ebrahimi16153.movieapp.utils.UserTokenDataStore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    // binding
    private lateinit var binding: FragmentRegisterBinding

    //dataStore
    @Inject
    private lateinit var userDataStore: UserTokenDataStore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initViews
        binding.apply {



        }


    }


}