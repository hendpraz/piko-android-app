package com.hpdev.piko.core.data

import androidx.lifecycle.LiveData
import com.hpdev.piko.core.data.source.local.LocalDataSource
import com.hpdev.piko.core.data.source.local.entity.UserEntity
import com.hpdev.piko.core.data.source.remote.RemoteDataSource
import com.hpdev.piko.core.data.source.remote.network.ApiResponse
import com.hpdev.piko.core.data.source.remote.response.UserResponse
import com.hpdev.piko.core.utils.AppExecutors
import com.hpdev.piko.core.utils.DataMapper

class UserRepository  private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(remoteData, localData, appExecutors)
            }
    }

    fun getAllUsers(): LiveData<Resource<List<UserEntity>>> =
        object : NetworkBoundResource<List<UserEntity>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<UserEntity>> {
                return localDataSource.getAllUser()
            }

            override fun shouldFetch(data: List<UserEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUsers()

            override fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asLiveData()

    fun getRecentUsers(): LiveData<Resource<List<UserEntity>>> =
        object : NetworkBoundResource<List<UserEntity>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<UserEntity>> {
                return localDataSource.getRecentUsers()
            }

            override fun shouldFetch(data: List<UserEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUsers()

            override fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asLiveData()

    fun getTopUsers(): LiveData<Resource<List<UserEntity>>> =
        object : NetworkBoundResource<List<UserEntity>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<UserEntity>> {
                return localDataSource.getTopUsers()
            }

            override fun shouldFetch(data: List<UserEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUsers()

            override fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asLiveData()

    fun getTopFavoriteUsers(): LiveData<List<UserEntity>> {
        return localDataSource.getTopFavoriteUsers()
    }

    fun getFavoriteUsers(): LiveData<List<UserEntity>> {
        return localDataSource.getTopFavoriteUsers()
    }

    fun setFavoriteUser(User: UserEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteUser(User, state) }
    }
}
