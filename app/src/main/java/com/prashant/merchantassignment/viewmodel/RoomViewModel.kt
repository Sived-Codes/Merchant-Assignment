package com.prashant.merchantassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.prashant.merchantassignment.model.UserModel
import com.prashant.merchantassignment.repository.UserRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomViewModel(private val repository: UserRoomRepository) : ViewModel() {

    val allItems: LiveData<List<UserModel>> = repository.getAllUser().asLiveData()

    fun insert(dbItem: UserModel) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.addUser(dbItem)
        }
    }

    fun delete(ids: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.delete(ids)
        }
    }

    fun getUserById(userId: Int): UserModel? {
        return repository.getUserById(userId)
    }

    suspend fun isUserExist(userId: Int): Boolean {
        return repository.isUserExists(userId)
    }

    suspend fun updateUser(userId: Int, firstName: String, lastName: String, email: String, mobile: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                repository.updateUser(userId, firstName, lastName, email, mobile)
                true
            } catch (e: Exception) {
                Log.e("RoomViewModel", "Error updating user: ${e.message}")
                false
            }
        }
    }
}
