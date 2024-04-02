package com.prashant.merchantassignment.core

import android.app.Application
import com.prashant.merchantassignment.room.database.UserDatabase
import com.prashant.merchantassignment.room.entity.UserLocalSource
import com.prashant.merchantassignment.repository.UserRoomRepository

class MyApplication : Application() {
    private val dataBase by lazy { UserDatabase.getDatabase(this) }
    private val userLocalSource by lazy { UserLocalSource (dataBase.userDao()) }
    val userRepository by lazy { UserRoomRepository(userLocalSource) }

}