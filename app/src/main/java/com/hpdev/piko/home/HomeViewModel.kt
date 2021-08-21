package com.hpdev.piko.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hpdev.piko.core.domain.usecase.UserUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(userUseCase: UserUseCase) : ViewModel() {
    val topFavorites = userUseCase.getTopFavoriteUsers().asLiveData()
    val recentUsers = userUseCase.getRecentUsers().asLiveData()
}