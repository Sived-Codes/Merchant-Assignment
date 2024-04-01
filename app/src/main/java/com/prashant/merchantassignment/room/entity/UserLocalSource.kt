package com.prashant.merchantassignment.room.entity

import com.prashant.merchantassignment.room.dao.UserDao
import kotlinx.coroutines.flow.Flow

class UserLocalSource(private val userDao: UserDao) {

    fun getAllUser(): Flow<List<UserRoomModel>> {
        return userDao.getAll()
    }

    fun add(user: UserRoomModel) {
        userDao.insertAll(user)
    }



    fun delete(movieId: List<Int>) {
        userDao.delete(movieId)
    }
}