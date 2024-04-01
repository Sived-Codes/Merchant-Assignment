package com.prashant.merchantassignment.network

import com.google.gson.Gson
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit as Retrofit1

object RetrofitClient  {
    private val retrofit : Retrofit1 by lazy {
      Retrofit1.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


}