package com.prashant.merchantassignment.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.prashant.merchantassignment.room.entity.UserRoomModel
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM users " )
    fun getAll(): Flow<List<UserRoomModel>>

    @Insert
    fun insertAll(vararg movieItems: UserRoomModel)

    @Query("DELETE FROM users WHERE id IN (:ids)")
    fun delete(ids: List<Int>)


}