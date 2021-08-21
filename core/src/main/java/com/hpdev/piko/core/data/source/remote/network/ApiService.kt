package com.hpdev.piko.core.data.source.remote.network

import com.hpdev.piko.core.data.source.remote.response.ListUserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getAllUsers(): ListUserResponse
}