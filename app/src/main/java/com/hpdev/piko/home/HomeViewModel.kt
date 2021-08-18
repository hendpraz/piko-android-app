package com.hpdev.piko.home

import androidx.lifecycle.ViewModel
import com.hpdev.piko.core.data.UserRepository

class HomeViewModel(userRepository: UserRepository) : ViewModel() {
    val topFavorites = userRepository.getTopFavoriteUsers()
    val recentUsers = userRepository.getRecentUsers()
}