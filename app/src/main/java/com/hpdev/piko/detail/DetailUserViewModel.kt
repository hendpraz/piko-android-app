package com.hpdev.piko.detail

import androidx.lifecycle.ViewModel
import com.hpdev.piko.core.data.UserRepository
import com.hpdev.piko.core.data.source.local.entity.UserEntity

class DetailUserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun setFavoriteUser(user: UserEntity, newStatus:Boolean) = userRepository.setFavoriteUser(user, newStatus)
}
