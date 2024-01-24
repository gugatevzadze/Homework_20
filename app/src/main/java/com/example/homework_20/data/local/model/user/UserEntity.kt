package com.example.homework_20.data.local.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name="first_name")
    val firstName:String,
    @ColumnInfo(name="last_name")
    val lastName:String,
    @ColumnInfo(name="email")
    val email:String,
    @ColumnInfo(name="age")
    val age:String,
)