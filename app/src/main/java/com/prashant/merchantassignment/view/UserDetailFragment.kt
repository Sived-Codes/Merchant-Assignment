package com.prashant.merchantassignment.view

import NetworkReceiver
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.prashant.merchantassignment.R
import com.prashant.merchantassignment.core.MyApplication
import com.prashant.merchantassignment.databinding.FragmentUserDetailBinding
import com.prashant.merchantassignment.model.UserModel
import com.prashant.merchantassignment.viewmodel.RoomViewModel
import com.prashant.merchantassignment.viewmodel.UserViewModel
import com.prashant.merchantassignment.viewmodel.UserViewModelFactory

class UserDetailFragment : Fragment() {

    private lateinit var bind: FragmentUserDetailBinding
    private val userViewModel: UserViewModel by viewModels()
    private val roomViewModel: RoomViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as MyApplication).userRepository)
    }
    private var userId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bind = FragmentUserDetailBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId = arguments?.getInt("id") ?: -1

        if (userId == -1) {
            return
        }


    }

    override fun onResume() {
        super.onResume()
        observeUserDetails()
        setupViews()
    }

    private fun observeUserDetails() {
        if (!NetworkReceiver.isOnline(requireContext())) {
            getUserOffline()
        } else {
            getUserOnline()
        }
    }

    private fun getUserOffline() {
        roomViewModel.getUserById(userId).observe(viewLifecycleOwner) { user ->
            user?.let {
                bindUserData(it)
            }
        }
    }

    private fun getUserOnline() {
        userViewModel.fetchUserById(userId)
        userViewModel.userDetails.observe(viewLifecycleOwner) { user ->
            user?.let {
                bindUserData(it)
            }
        }
    }

    private fun bindUserData(user: UserModel) {
        bind.apply {
            userName.text = "${user.firstName} ${user.lastName}"
            userEmail.text = user.email
            userMobile.text = user.phone

            profile.load(user.image) {
                crossfade(true)
                placeholder(R.drawable.icn_user)
            }
            mainLayout.visibility = View.VISIBLE
            pd.visibility = View.GONE
        }
    }

    private fun setupViews() {
        bind.editBtn.setOnClickListener {
            if (userId != -1) {
                val bundle = Bundle().apply {
                    putInt("id", userId)
                }
                findNavController().navigate(
                    R.id.action_userDetailFragment_to_userEditFragment, bundle
                )
            }
        }
    }
}
