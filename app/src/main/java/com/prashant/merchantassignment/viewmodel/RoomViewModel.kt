package com.prashant.merchantassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.prashant.merchantassignment.room.entity.UserRoomModel
import com.prashant.merchantassignment.room.repository.UserRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class RoomViewModel (private val repository: UserRoomRepository) : ViewModel() {

    val allItems: LiveData<List<UserRoomModel>> = repository.getAllUser().asLiveData()

    fun insert(dbItem: UserRoomModel) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.addUser(dbItem)
        }
    }

    fun delete(ids: List<Int>) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.delete(ids)
        }
    }

    fun updateTitle(id: Int, name: String, email: String, mobile: String) = viewModelScope.launch {
        withContext(Dispatchers.IO)
        {
        }
    }
}