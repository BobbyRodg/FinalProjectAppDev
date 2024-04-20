package com.example.woof.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserByID(id: Int) : User

    @Query("SELECT * FROM user WHERE username = :username")
    fun getUserByUsername(username: String) : User?

    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>
}