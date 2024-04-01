package com.prashant.merchantassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import coil.load
import com.prashant.merchantassignment.R
import com.prashant.merchantassignment.databinding.FragmentUserDetailBinding
import com.prashant.merchantassignment.databinding.FragmentUserListBinding
import com.prashant.merchantassignment.viewmodel.UserViewModel


class UserDetailFragment : Fragment() {

    private lateinit var bind: FragmentUserDetailBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bind = FragmentUserDetailBinding.inflate(inflater, container, false)
        val navController = activity?.findNavController(R.id.navHost)
        val id = arguments?.getString("id")

        getData()

        bind.editBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id", id!!)
            navController!!.navigate(R.id.action_userDetailFragment_to_userEditFragment, bundle)
        }

        return bind.root
    }

    private fun getData() {

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]


        userViewModel.specific.observe(viewLifecycleOwner) { user ->

            bind.userName.text = user.firstName + " " +user.lastName
            bind.userEmail.text = user.email
            bind.userMobile.text = user.phone

            bind.userImage.load(user.image) {
                crossfade(true)
                placeholder(R.drawable.icn_user)
            }
        }


    }
}