package com.hpdev.piko.di

import com.hpdev.piko.contacts.ContactsViewModel
import com.hpdev.piko.core.domain.usecase.UserInteractor
import com.hpdev.piko.core.domain.usecase.UserUseCase
import com.hpdev.piko.detail.DetailUserViewModel
import com.hpdev.piko.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ContactsViewModel(get()) }
    viewModel { DetailUserViewModel(get()) }
}