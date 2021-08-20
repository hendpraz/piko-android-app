package com.hpdev.piko.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hpdev.piko.contacts.ContactsViewModel
import com.hpdev.piko.core.di.Injection
import com.hpdev.piko.core.domain.usecase.UserUseCase
import com.hpdev.piko.detail.DetailUserViewModel
import com.hpdev.piko.favorites.FavoritesViewModel
import com.hpdev.piko.home.HomeViewModel

class ViewModelFactory private constructor(private val userUseCase: UserUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                    instance
                        ?: ViewModelFactory(
                            Injection.provideUserUseCase(
                                context
                            )
                        )
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(userUseCase) as T
            }
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(userUseCase) as T
            }
            modelClass.isAssignableFrom(DetailUserViewModel::class.java) -> {
                DetailUserViewModel(userUseCase) as T
            }
            modelClass.isAssignableFrom(ContactsViewModel::class.java) -> {
                ContactsViewModel(userUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}