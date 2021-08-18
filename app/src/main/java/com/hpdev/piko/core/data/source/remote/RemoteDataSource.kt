package com.hpdev.piko.core.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hpdev.piko.core.data.source.remote.network.ApiResponse
import com.hpdev.piko.core.data.source.remote.response.UserResponse
import com.hpdev.piko.core.utils.JsonHelper
import org.json.JSONException

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper)
                }
    }

    fun getAllUsers(): LiveData<ApiResponse<List<UserResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<UserResponse>>>()

        // get data from local json
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            try {
                val dataArray = jsonHelper.loadAllUsers()
                if (dataArray.isNotEmpty()) {
                    resultData.value = ApiResponse.Success(dataArray)
                } else {
                    resultData.value = ApiResponse.Empty
                }
            } catch (e: JSONException){
                resultData.value = ApiResponse.Error(e.toString())
                Log.e("RemoteDataSource", e.toString())
            }
        }, 2000)

        return resultData
    }

    fun getTopUsers(): LiveData<ApiResponse<List<UserResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<UserResponse>>>()

        // get data from local json
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            try {
                val dataArray = jsonHelper.loadTopUsers()
                if (dataArray.isNotEmpty()) {
                    resultData.value = ApiResponse.Success(dataArray)
                } else {
                    resultData.value = ApiResponse.Empty
                }
            } catch (e: JSONException){
                resultData.value = ApiResponse.Error(e.toString())
                Log.e("RemoteDataSource", e.toString())
            }
        }, 2000)

        return resultData
    }

    fun getRecentUsers(): LiveData<ApiResponse<List<UserResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<UserResponse>>>()

        // get data from local json
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            try {
                val dataArray = jsonHelper.loadRecentUsers()
                if (dataArray.isNotEmpty()) {
                    resultData.value = ApiResponse.Success(dataArray)
                } else {
                    resultData.value = ApiResponse.Empty
                }
            } catch (e: JSONException){
                resultData.value = ApiResponse.Error(e.toString())
                Log.e("RemoteDataSource", e.toString())
            }
        }, 2000)

        return resultData
    }
}