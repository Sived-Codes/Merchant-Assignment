package com.prashant.merchantassignment.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.prashant.merchantassignment.core.MyApplication
import com.prashant.merchantassignment.databinding.FragmentUserEditBinding
import com.prashant.merchantassignment.viewmodel.RoomViewModel
import com.prashant.merchantassignment.viewmodel.UserViewModelFactory
import kotlinx.coroutines.launch


class UserEditFragment : Fragment() {


    private lateinit var bind: FragmentUserEditBinding

    private val roomViewModel: RoomViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as MyApplication).userRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        bind = FragmentUserEditBinding.inflate(inflater, container, false)

        setupUi()
        return bind.root
    }

    private fun setupUi() {
        val userId = arguments?.getInt("id") ?: -1
        if (userId == -1) {
            return
        }

        bind.updateBtn.setOnClickListener {
            val fName = bind.updateFirstName.text.toString()
            val lName = bind.updateLastName.text.toString()
            val email = bind.updateEmail.text.toString()
            val mobile = bind.updateMobile.text.toString()

            if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || mobile.isEmpty()){
                Toast.makeText(requireContext(), "Fill All Details!", Toast.LENGTH_LONG).show()
            } else {
                lifecycleScope.launch {
                    val userUpdated = roomViewModel.updateUser(userId, fName, lName, email, mobile)
                    if (userUpdated) {
                        Toast.makeText(requireContext(), "User updated successfully!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Failed to update user!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}