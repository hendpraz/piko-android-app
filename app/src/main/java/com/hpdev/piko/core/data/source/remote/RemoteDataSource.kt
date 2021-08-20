package com.hpdev.piko.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hpdev.piko.core.data.source.remote.network.ApiResponse
import com.hpdev.piko.core.data.source.remote.network.ApiService
import com.hpdev.piko.core.data.source.remote.response.ListUserResponse
import com.hpdev.piko.core.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(service)
                }
    }

    fun getAllUsers(): LiveData<ApiResponse<List<UserResponse>>> {
        //get data from remote api
        val client = apiService.getAllUsers()

        return getData(client)
    }

    fun getTopUsers(): LiveData<ApiResponse<List<UserResponse>>> {
        //get data from remote api
        val client = apiService.getTopUsers()

        return getData(client)
    }

    fun getRecentUsers(): LiveData<ApiResponse<List<UserResponse>>> {
        //get data from remote api
        val client = apiService.getRecentUsers()

        return getData(client)
    }

    private fun getData(client: Call<ListUserResponse>) : LiveData<ApiResponse<List<UserResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<UserResponse>>>()
        client.enqueue(object : Callback<ListUserResponse> {
            override fun onResponse(
                call: Call<ListUserResponse>,
                response: Response<ListUserResponse>
            ) {
                val dataArray = response.body()?.users
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }
            override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }
}