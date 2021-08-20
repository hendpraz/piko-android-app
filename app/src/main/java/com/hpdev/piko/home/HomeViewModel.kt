package com.hpdev.piko.home

import androidx.lifecycle.ViewModel
import com.hpdev.piko.core.domain.usecase.UserUseCase

class HomeViewModel(userUseCase: UserUseCase) : ViewModel() {
    val topFavorites = userUseCase.getTopFavoriteUsers()
    val recentUsers = userUseCase.getRecentUsers()
}