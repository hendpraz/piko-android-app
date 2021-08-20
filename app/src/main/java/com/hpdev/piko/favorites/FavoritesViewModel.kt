package com.hpdev.piko.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hpdev.piko.core.domain.usecase.UserUseCase
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(userUseCase: UserUseCase) : ViewModel() {
    val favoriteUsers = userUseCase.getFavoriteUsers().asLiveData()
}

