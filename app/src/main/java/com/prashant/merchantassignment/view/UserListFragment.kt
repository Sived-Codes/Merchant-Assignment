package com.prashant.merchantassignment.view

import com.prashant.merchantassignment.viewmodel.UserViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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
import com.prashant.merchantassignment.viewmodel.UserViewModelFactory
import kotlinx.coroutines.launch
import kotlin.random.Random


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


        setupRecyclerView()
        fetchUserOnline()
        fetchUserOffline()

        bind.addBtn.setOnClickListener {
            showAddDialog()
        }
        return bind.root
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
                    val model = UserModel(Random.nextInt(100, 10000), fNameT, lNameT, emailT, mobileT, "https://robohash.org/Terry.png?set=set4")
                    roomViewModel.insert(model)
                    Toast.makeText(requireContext(), "Added $fNameT", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
            }
        }

        dialog.show()
    }



    private fun fetchUserOnline() {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.fetchUsers(roomViewModel);

    }


    private fun fetchUserOffline() {

        roomViewModel.allItems.observe(viewLifecycleOwner) { userList ->

            if(userList.isEmpty()){
                Toast.makeText(requireContext(), "Please fetch data online first ! ", Toast.LENGTH_SHORT).show()
                userViewModel.list.observe(viewLifecycleOwner) { userList ->
                    adapter.updateUserList(userList)
                }
            }else{
                adapter.updateUserList(userList)

            }

        }


    }

    private fun setupRecyclerView() {
        val navController = activity?.findNavController(R.id.navHost)
        bind.userRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserAdapter(roomViewModel, emptyList(), navController)
        bind.userRecyclerview.adapter = adapter
        bind.pd.visibility =View.GONE
    }

}