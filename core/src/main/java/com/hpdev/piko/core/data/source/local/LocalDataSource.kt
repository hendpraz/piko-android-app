package com.hpdev.piko.core.data.source.local

import com.hpdev.piko.core.data.source.local.entity.UserEntity
import com.hpdev.piko.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val userDao: UserDao) {
    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()

    fun getRecentUsers(): Flow<List<UserEntity>> = userDao.getRecentUsers()

    fun getTopUsers(): Flow<List<UserEntity>> = userDao.getTopUsers()

    fun getFavoriteUsers(): Flow<List<UserEntity>> = userDao.getFavoriteUsers()

    fun getTopFavoriteUsers(): Flow<List<UserEntity>> = userDao.getTopFavoriteUsers()

    suspend fun insertUser(userList: List<UserEntity>) = userDao.insertUsers(userList)

    fun setFavoriteUser(user: UserEntity, newState: Boolean) {
        user.isFavorite = newState
        userDao.updateUser(user)
    }

    fun addUser(userEntity: UserEntity) = userDao.insertUser(userEntity)

    fun deleteUser(userEntity: UserEntity) = userDao.deleteUser(userEntity)

    fun updateUser(userEntity: UserEntity) = userDao.updateUser(userEntity)

    fun setMainCategoryUser(userEntity: UserEntity, mainCategory: String) {
        userEntity.mainCategory = mainCategory
        userDao.updateUser(userEntity)
    }

    fun setMainContactUser(userEntity: UserEntity, mainContact: String) {
        userEntity.mainContact = mainContact
        userDao.updateUser(userEntity)
    }
}