package com.hpdev.piko.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hpdev.piko.contacts.ContactsViewModel
import com.hpdev.piko.core.ui.ViewModelFactory
import com.hpdev.piko.detail.DetailUserViewModel
import com.hpdev.piko.favorites.FavoritesViewModel
import com.hpdev.piko.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailUserViewModel::class)
    abstract fun bindDetailUserViewModel(viewModel: DetailUserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactsViewModel::class)
    abstract fun bindContactsViewModel(viewModel: ContactsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}