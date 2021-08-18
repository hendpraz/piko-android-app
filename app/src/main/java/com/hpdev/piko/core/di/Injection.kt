package com.hpdev.piko.core.di

import android.content.Context
import com.hpdev.piko.core.data.UserRepository
import com.hpdev.piko.core.data.source.local.LocalDataSource
import com.hpdev.piko.core.data.source.local.room.UserDatabase
import com.hpdev.piko.core.data.source.remote.RemoteDataSource
import com.hpdev.piko.core.utils.AppExecutors
import com.hpdev.piko.core.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val database = UserDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.userDao())
        val appExecutors = AppExecutors()

        return UserRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
