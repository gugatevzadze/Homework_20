package com.example.homework_20.data.local.dao.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.homework_20.data.local.model.user.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("SELECT * FROM userentity WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT COUNT(*) FROM userentity")
    suspend fun getUserCount(): Int


}