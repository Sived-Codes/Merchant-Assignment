package com.prashant.merchantassignment.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.prashant.merchantassignment.R
import com.prashant.merchantassignment.apdater.UserAdapter
import com.prashant.merchantassignment.core.MyApplication
import com.prashant.merchantassignment.databinding.FragmentUserListBinding
import com.prashant.merchantassignment.model.UserModel
import com.prashant.merchantassignment.viewmodel.RoomViewModel
import com.prashant.merchantassignment.viewmodel.UserViewModel
import com.prashant.merchantassignment.viewmodel.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


class UserListFragment : Fragment() {

    private lateinit var bind: FragmentUserListBinding
    private lateinit var adapter: UserAdapter

    private val userViewModel: UserViewModel by viewModels()
    private val roomViewModel: RoomViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as MyApplication).userRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bind = FragmentUserListBinding.inflate(inflater, container, false)

        val navController = activity?.findNavController(R.id.navHost)
        bind.userRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserAdapter(roomViewModel, emptyList(), navController)
        bind.userRecyclerview.adapter = adapter

        fetchUserOnline()
        fetchUserOffline()

        bind.addBtn.setOnClickListener {
            showAddDialog()
        }

        bind.refreshBtn.setOnClickListener {
            fetchUserOnline()
        }

        return bind.root
    }

    private fun fetchUserOffline() {
        roomViewModel.allItems.observe(viewLifecycleOwner) { userList ->
            if (userList.isEmpty()) {
                bind.noUserFound.visibility = View.VISIBLE
            } else {
                bind.noUserFound.visibility = View.GONE
                adapter.updateUserList(userList)
            }
            bind.pd.visibility = View.GONE

        }
    }

    private fun fetchUserOnline() {
        userViewModel.fetchUsers()
        userViewModel.list.observe(viewLifecycleOwner) { userList ->
            saveUser(userList)
        }
    }

    private fun showAddDialog() {
        val view = layoutInflater.inflate(R.layout.cl_add_contact, null)
        val dialog = AlertDialog.Builder(requireContext()).apply {
            setView(view)
        }.create()

        val fName = view.findViewById<EditText>(R.id.updateFirstName)
        val lName = view.findViewById<EditText>(R.id.updateLastName)
        val mobile = view.findViewById<EditText>(R.id.updateMobile)
        val email = view.findViewById<EditText>(R.id.updateEmail)
        val addBtn = view.findViewById<MaterialButton>(R.id.updateBtn)

        addBtn.setOnClickListener {
            val fNameT = fName.text.toString()
            val lNameT = lName.text.toString()
            val emailT = email.text.toString()
            val mobileT = mobile.text.toString()

            if (fNameT.isEmpty() || lNameT.isEmpty() || emailT.isEmpty() || mobileT.isEmpty()) {
                Toast.makeText(requireContext(), "Fill All Details!", Toast.LENGTH_LONG).show()
            } else {
                lifecycleScope.launch {
                    val model = UserModel(
                        Random.nextInt(100, 10000),
                        fNameT,
                        lNameT,
                        emailT,
                        mobileT,
                        "https://robohash.org/Terry.png?set=set4"
                    )
                    roomViewModel.insert(model)
                    Toast.makeText(requireContext(), "Added $fNameT", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
            }
        }

        dialog.show()
    }


    private fun saveUser(userList: List<UserModel>) {
        bind.pd.visibility = View.VISIBLE

        lifecycleScope.launch {
            var insertedUserCount = 0
            for (user in userList) {
                withContext(Dispatchers.IO) {
                    val existingUser = roomViewModel.getUserById(user.id)
                    if (existingUser == null) {
                        roomViewModel.insert(user)
                        insertedUserCount++
                    }
                }
            }
            bind.pd.visibility = View.GONE

        }
    }

}
