package com.hpdev.piko.core.data

import com.hpdev.piko.core.data.source.local.LocalDataSource
import com.hpdev.piko.core.data.source.remote.RemoteDataSource
import com.hpdev.piko.core.data.source.remote.network.ApiResponse
import com.hpdev.piko.core.data.source.remote.response.UserResponse
import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.domain.repository.IUserRepository
import com.hpdev.piko.core.utils.AppExecutors
import com.hpdev.piko.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {

    override fun getAllUsers(): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource.getAllUsers().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<User>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUsers()

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asFlow()

    override fun getRecentUsers(): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource.getRecentUsers().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<User>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUsers()

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asFlow()

    override fun getTopUsers(): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource.getTopUsers().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<User>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUsers()

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }
        }.asFlow()

    override fun getTopFavoriteUsers(): Flow<List<User>> {
        return localDataSource.getTopFavoriteUsers().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun getFavoriteUsers(): Flow<List<User>> {
        return localDataSource.getFavoriteUsers().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteUser(user: User, state: Boolean) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.setFavoriteUser(userEntity, state) }
    }

    override fun addUser(user: User) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.addUser(userEntity) }
    }

    override fun deleteUser(user: User) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.deleteUser(userEntity) }
    }

    override fun updateUser(user: User) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.updateUser(userEntity) }
    }

    override fun setMainContactUser(user: User, mainContact: String) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.setMainContactUser(userEntity, mainContact) }
    }

    override fun setMainCategoryUser(user: User, mainCategory: String) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.setMainCategoryUser(userEntity, mainCategory) }
    }
}