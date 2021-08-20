package com.hpdev.piko.core.di

import com.hpdev.piko.core.data.UserRepository
import com.hpdev.piko.core.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(userRepository: UserRepository): IUserRepository

}