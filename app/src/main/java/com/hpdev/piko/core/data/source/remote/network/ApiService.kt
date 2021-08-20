package com.hpdev.piko.core.data.source.remote.network

import com.hpdev.piko.core.data.source.remote.response.ListUserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getAllUsers(): Call<ListUserResponse>

    @GET("recent-users")
    fun getRecentUsers(): Call<ListUserResponse>

    @GET("top-users")
    fun getTopUsers(): Call<ListUserResponse>
}