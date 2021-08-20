package com.hpdev.piko.history

import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val historyModule = module {
    viewModel { HistoryViewModel(get()) }
}