package com.prashant.merchantassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prashant.merchantassignment.model.UsersModel
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    private val repository = UserRepository()
    private  val getUsers = MutableLiveData<List<UsersModel>>()
    val list: LiveData<List<UsersModel>> get() = getUsers

    val specific: LiveData<UsersModel> get() = specificUser

    private val specificUser = MutableLiveData<UsersModel>()

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

    fun getSpecificUser(id : Int){
        viewModelScope.launch {
            try {
                specificUser.value = repository.getUserById(id)
            }catch (e: Exception){
                Log.d("ViewModelError", "getUsers: " +e.toString()  )
            }
        }
    }

}