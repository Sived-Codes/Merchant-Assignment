package com.prashant.merchantassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.prashant.merchantassignment.R
import com.prashant.merchantassignment.databinding.FragmentUserDetailBinding
import com.prashant.merchantassignment.viewmodel.UserViewModel

class UserDetailFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getInt("id") ?: -1
        if (userId == -1) {
            return
        }

        setupViews()
        userViewModel.getSpecificUser(userId)
        observeUserData()
    }

    private fun setupViews() {
        binding.editBtn.setOnClickListener {
            val userId = arguments?.getInt("id") ?: -1
            if (userId != -1) {
                val bundle = Bundle().apply {
                    putInt("id", userId)
                }
                findNavController().navigate(R.id.action_userDetailFragment_to_userEditFragment, bundle)
            }
        }
    }

    private fun observeUserData() {
        userViewModel.specific.observe(viewLifecycleOwner) { user ->
            binding.apply {
                userName.text = "${user.firstName} ${user.lastName}"
                userEmail.text = user.email
                userMobile.text = user.phone

                userImage.load(user.image) {
                    crossfade(true)
                    placeholder(R.drawable.icn_user)
                }
            }
        }
    }
}
