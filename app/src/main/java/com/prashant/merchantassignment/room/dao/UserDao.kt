package com.prashant.merchantassignment.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.prashant.merchantassignment.model.UserModel
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM users " )
    fun getAll(): Flow<List<UserModel>>

    @Insert
    fun insertAll(vararg movieItems: UserModel)

    @Query("DELETE FROM users WHERE id IN (:ids)")
    fun delete(ids: List<Int>)


}