package com.hpdev.piko.detail

import androidx.lifecycle.ViewModel
import com.hpdev.piko.core.data.UserRepository
import com.hpdev.piko.core.domain.model.User

class DetailUserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun setFavoriteUser(user: User, newStatus:Boolean) = userRepository.setFavoriteUser(user, newStatus)
}
