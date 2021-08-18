package com.hpdev.piko.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hpdev.piko.contacts.ContactsViewModel
import com.hpdev.piko.core.data.UserRepository
import com.hpdev.piko.core.di.Injection
import com.hpdev.piko.detail.DetailUserViewModel
import com.hpdev.piko.favorites.FavoritesViewModel
import com.hpdev.piko.home.HomeViewModel

class ViewModelFactory private constructor(private val userRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                    instance
                        ?: ViewModelFactory(
                            Injection.provideRepository(
                                context
                            )
                        )
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(DetailUserViewModel::class.java) -> {
                DetailUserViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(ContactsViewModel::class.java) -> {
                ContactsViewModel(userRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}