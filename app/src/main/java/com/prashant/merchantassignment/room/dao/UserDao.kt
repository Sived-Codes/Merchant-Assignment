package com.prashant.merchantassignment.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.prashant.merchantassignment.model.UserModel
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): Flow<List<UserModel>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Int): UserModel?

    @Insert
    fun insertAll(vararg movieItems: UserModel)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: Int)

    @Query("UPDATE users SET firstName = :firstName, lastName = :lastName, email = :email, phone = :mobile WHERE id = :userId")
    suspend fun updateUser(userId: Int, firstName: String, lastName: String, email: String, mobile: String)

}