package com.hpdev.piko.core.domain.usecase

import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.domain.repository.IUserRepository

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase{
    override fun getAllUsers() = userRepository.getAllUsers()

    override fun getRecentUsers() = userRepository.getRecentUsers()

    override fun getTopUsers() = userRepository.getTopUsers()

    override fun getTopFavoriteUsers() = userRepository.getTopFavoriteUsers()

    override fun getFavoriteUsers() = userRepository.getFavoriteUsers()

    override fun setFavoriteUser(user: User, state: Boolean) = userRepository.setFavoriteUser(user, state)

    override fun addUser(user: User) = userRepository.addUser(user)

    override fun deleteUser(user: User) = userRepository.deleteUser(user)

    override fun updateUser(user: User) = userRepository.updateUser(user)

    override fun setMainContactUser(user: User, mainContact: String) = userRepository.setMainContactUser(user, mainContact)

    override fun setMainCategoryUser(user: User, mainCategory: String) = userRepository.setMainCategoryUser(user, mainCategory)


}