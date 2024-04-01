package com.prashant.merchantassignment.room.repository

import com.prashant.merchantassignment.room.entity.UserRoomModel
import com.prashant.merchantassignment.room.entity.UserLocalSource
import kotlinx.coroutines.flow.Flow


class UserRoomRepository(private val localSource: UserLocalSource) {
    fun getAllUser(): Flow<List<UserRoomModel>>
    {
        return localSource.getAllUser()
    }

    fun addUser(user: UserRoomModel) {
        localSource.add(user)
    }


    fun delete(ids: List<Int>) {
        localSource.delete(ids)
    }
}