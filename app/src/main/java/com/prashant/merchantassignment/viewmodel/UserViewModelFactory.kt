package com.prashant.merchantassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prashant.merchantassignment.room.repository.UserRoomRepository

class UserViewModelFactory(private val userRepository: UserRoomRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            return RoomViewModel(userRepository) as T

        }

        throw IllegalArgumentException("UnknowViewmodel")
    }

}