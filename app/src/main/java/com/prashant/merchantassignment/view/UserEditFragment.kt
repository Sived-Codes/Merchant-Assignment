package com.prashant.merchantassignment.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.prashant.merchantassignment.core.MyApplication
import com.prashant.merchantassignment.databinding.FragmentUserEditBinding
import com.prashant.merchantassignment.viewmodel.RoomViewModel
import com.prashant.merchantassignment.viewmodel.UserViewModel
import com.prashant.merchantassignment.viewmodel.UserViewModelFactory


class UserEditFragment : Fragment() {


    private lateinit var bind: FragmentUserEditBinding
    private lateinit var userViewModel: UserViewModel

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


        bind.updateBtn.setOnClickListener {

        }
    }


}