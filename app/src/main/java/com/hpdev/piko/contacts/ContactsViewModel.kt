package com.hpdev.piko.contacts

import androidx.lifecycle.ViewModel
import com.hpdev.piko.core.data.UserRepository

class ContactsViewModel(userRepository: UserRepository) : ViewModel() {
    val allUsers = userRepository.getAllUsers()
    val topUsers = userRepository.getTopUsers()
}