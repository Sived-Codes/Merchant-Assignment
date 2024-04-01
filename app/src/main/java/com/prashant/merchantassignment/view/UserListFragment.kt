package com.prashant.merchantassignment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.prashant.merchantassignment.R
import com.prashant.merchantassignment.apdater.UserAdapter
import com.prashant.merchantassignment.core.MyApplication
import com.prashant.merchantassignment.databinding.FragmentUserListBinding
import com.prashant.merchantassignment.model.UserModel
import com.prashant.merchantassignment.viewmodel.RoomViewModel
import com.prashant.merchantassignment.viewmodel.UserViewModel
import com.prashant.merchantassignment.viewmodel.UserViewModelFactory


class UserListFragment : Fragment() {

    private lateinit var bind: FragmentUserListBinding

    private lateinit var adapter: UserAdapter
    private lateinit var userViewModel: UserViewModel

    private val roomViewModel: RoomViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as MyApplication).userRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
            roomViewModel.allItems.observe(
                viewLifecycleOwner,
                Observer<List<UserModel>> { roomList ->
                    val mergedList = mergeUserLists(userList, roomList)

                    adapter = UserAdapter(requireContext(), mergedList, navController)
                    bind.userRecyclerview.adapter = adapter
                    bind.pd.visibility = View.GONE
                })
        }

    }

    private fun mergeUserLists(
        apiList: List<UserModel>, roomList: List<UserModel>
    ): List<UserModel> {
        val mergedList = mutableListOf<UserModel>()
        mergedList.addAll(apiList)

        roomList.forEach { roomItem ->
            val matchingApiItemIndex = mergedList.indexOfFirst { it.id == roomItem.id }
            if (matchingApiItemIndex != -1) {
                mergedList[matchingApiItemIndex] = roomItem
            }
        }

        return mergedList
    }


}