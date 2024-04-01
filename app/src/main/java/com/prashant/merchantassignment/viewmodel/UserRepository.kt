package com.prashant.merchantassignment.viewmodel

import com.prashant.merchantassignment.model.UsersModel
import com.prashant.merchantassignment.network.RetrofitClient

class UserRepository {
    suspend fun getUsers() : List<UsersModel>{
        return RetrofitClient.apiService.getUsers().users
    }

    suspend fun getUserById(id : Int) : UsersModel{
        return RetrofitClient.apiService.getUserById(id)
    }
}