package com.hpdev.piko.core.domain.usecase

import androidx.lifecycle.LiveData
import com.hpdev.piko.core.data.Resource
import com.hpdev.piko.core.domain.model.User

interface UserUseCase {
    fun getAllUsers(): LiveData<Resource<List<User>>>

    fun getRecentUsers() : LiveData<Resource<List<User>>>

    fun getTopUsers(): LiveData<Resource<List<User>>>

    fun getTopFavoriteUsers(): LiveData<List<User>>

    fun getFavoriteUsers(): LiveData<List<User>>

    fun setFavoriteUser(user: User, state: Boolean)
}