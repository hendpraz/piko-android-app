package com.hpdev.piko.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hpdev.piko.core.domain.usecase.UserUseCase

class HistoryViewModel (userUseCase: UserUseCase) : ViewModel() {
    val users = userUseCase.getAllUsers().asLiveData()
}