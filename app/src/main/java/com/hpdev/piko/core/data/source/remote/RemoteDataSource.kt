package com.hpdev.piko.core.data.source.remote

import android.util.Log
import com.hpdev.piko.core.data.source.remote.network.ApiResponse
import com.hpdev.piko.core.data.source.remote.network.ApiService
import com.hpdev.piko.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(service)
                }
    }

    suspend fun getAllUsers(): Flow<ApiResponse<List<UserResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getAllUsers()
                val dataArray = response.users
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.users))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}