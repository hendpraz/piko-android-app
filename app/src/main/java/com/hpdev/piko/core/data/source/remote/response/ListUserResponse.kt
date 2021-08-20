package com.hpdev.piko.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse (
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val users: List<UserResponse>
)