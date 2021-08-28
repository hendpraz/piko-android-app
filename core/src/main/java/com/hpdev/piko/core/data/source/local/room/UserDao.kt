package com.hpdev.piko.core.data.source.local.room

import androidx.room.*
import com.hpdev.piko.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users ORDER BY dateAdded DESC LIMIT 3 ")
    fun getRecentUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users LIMIT 6")
    fun getTopUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE isFavorite = 1")
    fun getFavoriteUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE isFavorite = 1 LIMIT 6")
    fun getTopFavoriteUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Update
    fun updateUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)
}