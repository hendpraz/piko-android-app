package com.hpdev.piko.core.domain.usecase

import androidx.lifecycle.LiveData
import com.hpdev.piko.core.data.Resource
import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.domain.repository.IUserRepository

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase{
    override fun getAllUsers(): LiveData<Resource<List<User>>> = userRepository.getAllUsers()

    override fun getRecentUsers(): LiveData<Resource<List<User>>> = userRepository.getRecentUsers()

    override fun getTopUsers(): LiveData<Resource<List<User>>> = userRepository.getTopUsers()

    override fun getTopFavoriteUsers(): LiveData<List<User>> = userRepository.getTopFavoriteUsers()

    override fun getFavoriteUsers(): LiveData<List<User>> = userRepository.getFavoriteUsers()

    override fun setFavoriteUser(user: User, state: Boolean) = userRepository.setFavoriteUser(user, state)

}