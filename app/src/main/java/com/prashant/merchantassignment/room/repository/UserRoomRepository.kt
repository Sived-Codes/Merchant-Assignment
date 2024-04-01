package com.prashant.merchantassignment.room.repository

import com.prashant.merchantassignment.model.UserModel
import com.prashant.merchantassignment.room.entity.UserLocalSource
import kotlinx.coroutines.flow.Flow


class UserRoomRepository(private val localSource: UserLocalSource) {
    fun getAllUser(): Flow<List<UserModel>>
    {
        return localSource.getAllUser()
    }

    fun addUser(user: UserModel) {
        localSource.add(user)
    }


    fun delete(ids: List<Int>) {
        localSource.delete(ids)
    }
}