package com.example.homework_20.data.local.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework_20.data.local.dao.user.UserDao
import com.example.homework_20.data.local.model.user.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}