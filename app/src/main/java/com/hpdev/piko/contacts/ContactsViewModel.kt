package com.hpdev.piko.contacts

import androidx.lifecycle.ViewModel
import com.hpdev.piko.core.domain.usecase.UserUseCase

class ContactsViewModel(userUseCase: UserUseCase) : ViewModel() {
    val allUsers = userUseCase.getAllUsers()
    val topUsers = userUseCase.getTopUsers()
}