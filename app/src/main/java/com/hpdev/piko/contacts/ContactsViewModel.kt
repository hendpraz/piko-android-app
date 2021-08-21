package com.hpdev.piko.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hpdev.piko.core.domain.usecase.UserUseCase

class ContactsViewModel(userUseCase: UserUseCase) : ViewModel() {
    val allUsers = userUseCase.getAllUsers().asLiveData()
    val topUsers = userUseCase.getTopUsers().asLiveData()
}