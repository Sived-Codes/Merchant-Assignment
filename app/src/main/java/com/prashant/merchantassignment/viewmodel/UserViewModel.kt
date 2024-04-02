package com.prashant.merchantassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prashant.merchantassignment.model.UserModel
import com.prashant.merchantassignment.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _users = MutableLiveData<List<UserModel>>()
    val list: LiveData<List<UserModel>> get() = _users


    private val _userDetails = MutableLiveData<UserModel>()
    val userDetails: LiveData<UserModel> get() = _userDetails

    fun fetchUsers(roomViewModel: RoomViewModel) {
        viewModelScope.launch {
            try {
                val userList = repository.getUsers()

                _users.value = userList
                for (newUser in userList) {

                    val localUser = roomViewModel.getUserById(newUser.id)

                    if (localUser == null) {
                        roomViewModel.insert(localUser)

                    }
                }
                Log.d("UserViewModel", "All users saved locally")
            } catch (e: Exception) {
                Log.e("UserViewModel", "fetchUsers: ${e.message}")
            }
        }
    }

    fun fetchUserById(userId: Int) {
        viewModelScope.launch {
            try {
                val user = repository.getUserById(userId)
                _userDetails.value = user
            } catch (e: Exception) {
                Log.e("ViewModel", "fetchUserById: ${e.message}")
            }
        }
    }
}
