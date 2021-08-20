package com.hpdev.piko.core.di

import android.content.Context
import androidx.room.Room
import com.hpdev.piko.core.data.source.local.room.UserDao
import com.hpdev.piko.core.data.source.local.room.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): UserDatabase = Room.databaseBuilder(
        context,
        UserDatabase::class.java, "Tourism.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: UserDatabase): UserDao = database.userDao()
}