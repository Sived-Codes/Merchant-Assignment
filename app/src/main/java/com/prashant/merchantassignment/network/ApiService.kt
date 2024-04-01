package com.prashant.merchantassignment.network

import com.prashant.merchantassignment.model.ResponseModel
import com.prashant.merchantassignment.model.UserModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers() : ResponseModel

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id : Int) : UserModel

}