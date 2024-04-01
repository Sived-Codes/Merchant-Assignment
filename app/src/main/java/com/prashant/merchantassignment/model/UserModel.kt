package com.prashant.merchantassignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserModel(
   @PrimaryKey val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val image: String,
)