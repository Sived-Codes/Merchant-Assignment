package com.prashant.merchantassignment.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prashant.merchantassignment.model.UsersModel
import com.prashant.merchantassignment.room.dao.UserDao
import com.prashant.merchantassignment.room.entity.UserRoomModel


@Database(entities = [UserRoomModel::class], exportSchema = true, version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun  userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(
            context: Context
        ): UserDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "users"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}