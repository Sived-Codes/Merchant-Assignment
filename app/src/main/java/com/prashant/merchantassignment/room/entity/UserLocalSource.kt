package com.prashant.merchantassignment.room.entity

import com.prashant.merchantassignment.model.UserModel
import com.prashant.merchantassignment.room.dao.UserDao
import kotlinx.coroutines.flow.Flow

class UserLocalSource(private val userDao: UserDao) {

    fun getAllUser(): Flow<List<UserModel>> {
        return userDao.getAll()
    }

    fun add(user: UserModel) {
        userDao.insertAll(user)
    }

    suspend fun delete(id: Int) {
        userDao.deleteUserById(id)
    }
    fun getUserById(userId: Int): Flow<UserModel?> {
        return userDao.getUserById(userId)
    }
    suspend fun updateUser(userId: Int, firstName: String, lastName: String, email: String, mobile: String): Boolean {
        val user = userDao.getUserById(userId)
        if (user != null) {
            userDao.updateUser(userId, firstName, lastName, email, mobile)
            return true
        }
        return false
    }
}