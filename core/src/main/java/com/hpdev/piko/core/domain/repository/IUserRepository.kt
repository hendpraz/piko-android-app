package com.hpdev.piko.core.domain.repository

import com.hpdev.piko.core.data.Resource
import com.hpdev.piko.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getAllUsers(): Flow<Resource<List<User>>>

    fun getRecentUsers() : Flow<Resource<List<User>>>

    fun getTopUsers(): Flow<Resource<List<User>>>

    fun getTopFavoriteUsers(): Flow<List<User>>

    fun getFavoriteUsers(): Flow<List<User>>

    fun setFavoriteUser(user: User, state: Boolean)

    fun addUser(user: User)

    fun deleteUser(user: User)

    fun updateUser(user: User)

    fun setMainContactUser(user: User, mainContact: String)

    fun setMainCategoryUser(user: User, mainCategory: String)
}