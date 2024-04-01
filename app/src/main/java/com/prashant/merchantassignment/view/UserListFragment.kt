package com.prashant.merchantassignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.prashant.merchantassignment.R
import com.prashant.merchantassignment.apdater.UserAdapter
import com.prashant.merchantassignment.databinding.FragmentUserListBinding
import com.prashant.merchantassignment.viewmodel.RoomViewModel
import com.prashant.merchantassignment.viewmodel.UserViewModel


class UserListFragment : Fragment() {

    private lateinit var bind: FragmentUserListBinding

    private  lateinit var  adapter : UserAdapter
    private  lateinit var  userViewModel: UserViewModel
    private val viewModel : RoomViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentUserListBinding.inflate(inflater, container, false)

        getUserList()
        return bind.root
    }

    private fun getUserList() {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        bind.userRecyclerview.layoutManager = LinearLayoutManager(requireContext())


        val navController = activity?.findNavController(R.id.navHost)

        userViewModel.list.observe(viewLifecycleOwner) { userList ->
            adapter = UserAdapter(requireContext(), userList, navController )
            bind.userRecyclerview.adapter = adapter
            bind.pd.visibility=View.GONE
        }

    }

}