package com.hpdev.piko.favorites

import androidx.lifecycle.ViewModel
import com.hpdev.piko.core.data.UserRepository

class FavoritesViewModel(userRepository: UserRepository) : ViewModel() {
    val favoriteUsers = userRepository.getFavoriteUsers()
}

