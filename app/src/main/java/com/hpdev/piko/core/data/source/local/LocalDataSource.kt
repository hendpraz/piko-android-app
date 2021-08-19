package com.hpdev.piko.core.data.source.local

import androidx.lifecycle.LiveData
import com.hpdev.piko.core.data.source.local.entity.UserEntity
import com.hpdev.piko.core.data.source.local.room.UserDao

class LocalDataSource private constructor(private val userDao: UserDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(userDao: UserDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(userDao)
            }
    }

    fun getAllUsers(): LiveData<List<UserEntity>> = userDao.getAllUsers()

    fun getRecentUsers(): LiveData<List<UserEntity>> = userDao.getRecentUsers()

    fun getTopUsers(): LiveData<List<UserEntity>> = userDao.getTopUsers()

    fun getFavoriteUsers(): LiveData<List<UserEntity>> = userDao.getFavoriteUsers()

    fun getTopFavoriteUsers(): LiveData<List<UserEntity>> = userDao.getTopFavoriteUsers()

    fun insertUser(userList: List<UserEntity>) = userDao.insertUsers(userList)

    fun setFavoriteUser(user: UserEntity, newState: Boolean) {
        user.isFavorite = newState
        userDao.updateFavoriteUser(user)
    }
}