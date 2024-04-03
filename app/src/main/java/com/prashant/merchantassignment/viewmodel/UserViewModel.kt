package com.prashant.merchantassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.prashant.merchantassignment.model.UserModel
import com.prashant.merchantassignment.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _users = MutableLiveData<List<UserModel>>()
    val list: LiveData<List<UserModel>> get() = _users


    private val _userDetails = MutableLiveData<UserModel>()
    val userDetails: LiveData<UserModel> get() = _userDetails

    init {
        fetchUsers()
    }
    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = repository.getUsers()
                _users.value = userList
            } catch (e: Exception) {
                Log.e("UserViewModel", "fetchUsers: ${e.message}")
            }
        }
    }

    fun fetchUserById(userId: Int, roomViewModel: RoomViewModel) {
        viewModelScope.launch {
            try {
                val user = withContext(Dispatchers.IO) {
                    repository.getUserById(userId)
                }
                _userDetails.value = user
            } catch (e: Exception) {
                val user = withContext(Dispatchers.IO) {
                    roomViewModel.getUserById(userId)
                }
                _userDetails.value = user!!
            }
        }
    }
}
