package com.hpdev.piko.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hpdev.piko.core.data.source.local.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM users LIMIT 3")
    fun getRecentUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM users LIMIT 6")
    fun getTopUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM users WHERE isFavorite = 1")
    fun getFavoriteUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM users WHERE isFavorite = 1 LIMIT 6")
    fun getTopFavoriteUsers(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserEntity>)

    @Update
    fun updateFavoriteUser(user: UserEntity)
}