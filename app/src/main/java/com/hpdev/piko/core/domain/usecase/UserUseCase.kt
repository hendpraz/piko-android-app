package com.hpdev.piko.core.domain.usecase

import com.hpdev.piko.core.data.Resource
import com.hpdev.piko.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUsers(): Flow<Resource<List<User>>>

    fun getRecentUsers() : Flow<Resource<List<User>>>

    fun getTopUsers(): Flow<Resource<List<User>>>

    fun getTopFavoriteUsers(): Flow<List<User>>

    fun getFavoriteUsers(): Flow<List<User>>

    fun setFavoriteUser(user: User, state: Boolean)
}