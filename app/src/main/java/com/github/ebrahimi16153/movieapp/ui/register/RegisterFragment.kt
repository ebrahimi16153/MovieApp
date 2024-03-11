package com.github.ebrahimi16153.movieapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.github.ebrahimi16153.movieapp.databinding.FragmentRegisterBinding
import com.github.ebrahimi16153.movieapp.models.register.BodyRegister
import com.github.ebrahimi16153.movieapp.utils.UserTokenDataStore
import com.github.ebrahimi16153.movieapp.utils.setVisibility
import com.github.ebrahimi16153.movieapp.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    // binding
    private lateinit var binding: FragmentRegisterBinding

    //dataStore
    @Inject
    lateinit var userDataStore: UserTokenDataStore

    // bodyRegister
//    @Inject
//    lateinit var bodyRegister: BodyRegister

    // registerViewModel


    private var bodyRegister: BodyRegister = BodyRegister()

    private val registerViewModel: RegisterViewModel by viewModels()


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



        binding.apply {


            btnSubmit.setOnClickListener { view ->
                val name = edtName.text.toString()
                val email = edtEmail.text.toString()
                val pass = edtPassword.text.toString()

                if (name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {

                    bodyRegister.name = name
                    bodyRegister.email = email
                    bodyRegister.password = pass
                    registerViewModel.registerUser(bodyRegister)

                } else {
                    Snackbar.make(
                        view,
                        "name email and password can't be empty",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

            registerViewModel.registerUser(bodyRegister)
            registerViewModel.loadingState.observe(viewLifecycleOwner) { isShow ->

                if (isShow) {
                    btnSubmit.setVisibility(true)
                    loadingBtnSubmit.setVisibility(false)
                } else {
                    loadingBtnSubmit.setVisibility(false)
                    btnSubmit.setVisibility(true)
                }

                registerViewModel.registerUser.observe(viewLifecycleOwner) { reponse ->

                    lifecycle.coroutineScope.launch {
                        userDataStore.saveUserToken(reponse.name)
                    }

                }
            }
        }
    }
}
