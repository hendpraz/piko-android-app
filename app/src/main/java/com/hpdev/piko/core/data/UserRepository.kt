package com.hpdev.piko.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hpdev.piko.core.data.source.local.LocalDataSource
import com.hpdev.piko.core.data.source.remote.RemoteDataSource
import com.hpdev.piko.core.data.source.remote.network.ApiResponse
import com.hpdev.piko.core.data.source.remote.response.UserResponse
import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.domain.repository.IUserRepository
import com.hpdev.piko.core.utils.AppExecutors
import com.hpdev.piko.core.utils.DataMapper

class UserRepository  private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {

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

    override fun getAllUsers(): LiveData<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<User>> {
                return Transformations.map(localDataSource.getAllUsers()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUsers()

            override fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asLiveData()

    override fun getRecentUsers(): LiveData<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<User>> {
                return Transformations.map(localDataSource.getRecentUsers()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUsers()

            override fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asLiveData()

    override fun getTopUsers(): LiveData<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<User>> {
                return Transformations.map(localDataSource.getTopUsers()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUsers()

            override fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asLiveData()

    override fun getTopFavoriteUsers(): LiveData<List<User>> {
        return Transformations.map(localDataSource.getTopFavoriteUsers()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteUsers(): LiveData<List<User>> {
        return Transformations.map(localDataSource.getFavoriteUsers()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteUser(user: User, state: Boolean) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.setFavoriteUser(userEntity, state) }
    }
}
