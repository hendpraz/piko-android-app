package com.hpdev.piko.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel { FavoritesViewModel(get()) }
}