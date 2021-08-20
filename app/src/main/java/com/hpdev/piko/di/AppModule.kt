package com.hpdev.piko.di

import com.hpdev.piko.core.domain.usecase.UserInteractor
import com.hpdev.piko.core.domain.usecase.UserUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase
}