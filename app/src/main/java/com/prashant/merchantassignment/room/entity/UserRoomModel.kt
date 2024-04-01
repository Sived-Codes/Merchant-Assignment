package com.prashant.merchantassignment.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserRoomModel(
    @PrimaryKey val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val image: String,
)