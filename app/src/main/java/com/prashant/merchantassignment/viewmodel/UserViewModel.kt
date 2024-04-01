package com.prashant.merchantassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prashant.merchantassignment.model.UserModel
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    private val repository = UserRepository()
    private  val getUsers = MutableLiveData<List<UserModel>>()
    val list: LiveData<List<UserModel>> get() = getUsers

    val specific: LiveData<UserModel> get() = specificUser

    private val specificUser = MutableLiveData<UserModel>()

    init {
        getUsers()
    }
    fun getUsers(){
        viewModelScope.launch {
            try {
                getUsers.value = repository.getUsers()
            }catch (e: Exception){
                Log.d("ViewModelError", "getUsers: " +e.toString()  )
            }
        }
    }

    fun getSpecificUser(id: Int) {
        viewModelScope.launch {
            try {
                val user = repository.getUserById(id)
                specificUser.value = user
            } catch (e: Exception) {
                Log.d("ViewModelError", "getSpecificUser: ${e.message}")
            }
        }
    }


}