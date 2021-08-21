package com.hpdev.piko.core.domain.usecase

import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.domain.repository.IUserRepository
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: IUserRepository) : UserUseCase{
    override fun getAllUsers() = userRepository.getAllUsers()

    override fun getRecentUsers() = userRepository.getRecentUsers()

    override fun getTopUsers() = userRepository.getTopUsers()

    override fun getTopFavoriteUsers() = userRepository.getTopFavoriteUsers()

    override fun getFavoriteUsers() = userRepository.getFavoriteUsers()

    override fun setFavoriteUser(user: User, state: Boolean) = userRepository.setFavoriteUser(user, state)

}