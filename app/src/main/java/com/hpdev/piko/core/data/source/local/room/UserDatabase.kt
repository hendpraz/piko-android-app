package com.hpdev.piko.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hpdev.piko.core.data.source.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase =
                INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                            context.applicationContext,
                            UserDatabase::class.java,
                            "UserContact.db"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                    instance
                }
    }
}