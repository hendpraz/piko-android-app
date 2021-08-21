package com.hpdev.piko.detail

import androidx.lifecycle.ViewModel
import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.domain.usecase.UserUseCase
import javax.inject.Inject

class DetailUserViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    fun setFavoriteUser(user: User, newStatus:Boolean) = userUseCase.setFavoriteUser(user, newStatus)
}
