package com.prashant.merchantassignment.viewmodel

import com.prashant.merchantassignment.model.UserModel
import com.prashant.merchantassignment.network.RetrofitClient

class UserRepository {
    suspend fun getUsers() : List<UserModel>{
        return RetrofitClient.apiService.getUsers().users
    }

    suspend fun getUserById(id : Int) : UserModel{
        return RetrofitClient.apiService.getUserById(id)
    }
}