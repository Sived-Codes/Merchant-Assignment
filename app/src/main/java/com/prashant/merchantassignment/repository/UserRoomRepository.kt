package com.prashant.merchantassignment.repository

import com.prashant.merchantassignment.model.UserModel
import com.prashant.merchantassignment.room.entity.UserLocalSource
import kotlinx.coroutines.flow.Flow


class UserRoomRepository(private val localSource: UserLocalSource) {
    fun getAllUser(): Flow<List<UserModel>> {
        return localSource.getAllUser()
    }

    fun addUser(user: UserModel) {
        localSource.add(user)
    }

    fun getUserById(userId: Int): UserModel? {
        return localSource.getUserById(userId)
    }

    suspend fun isUserExists(userId: Int): Boolean {
        return localSource.isUserExist(userId)
    }
   suspend fun delete(id: Int) {
        localSource.delete(id)
    }

    suspend fun updateUser(userId: Int, firstName: String, lastName: String, email: String, mobile: String): Boolean {
        return localSource.updateUser(userId, firstName, lastName, email, mobile)
    }
}