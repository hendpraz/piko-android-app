package com.hpdev.piko.favorites

import androidx.lifecycle.ViewModel
import com.hpdev.piko.core.domain.usecase.UserUseCase

class FavoritesViewModel(userUseCase: UserUseCase) : ViewModel() {
    val favoriteUsers = userUseCase.getFavoriteUsers()
}

