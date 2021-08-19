package com.hpdev.piko.core.utils

import android.content.Context
import com.hpdev.piko.R
import com.hpdev.piko.core.data.source.remote.response.UserResponse
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(jsonResource: Int): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(jsonResource).bufferedReader()
                    .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun loadData(jsonResource: Int): List<UserResponse> {
        val list = ArrayList<UserResponse>()
        val responseObject = JSONObject(parsingFileToString(jsonResource).toString())
        val listArray = responseObject.getJSONArray("data")

        for (i in 0 until listArray.length()) {
            val user = listArray.getJSONObject(i)

            val id = user.getInt("id")
            val fullName = user.getString("first_name") + " " + user.getString("last_name")
            val nickname = user.getString("first_name")
            val mainCategory = "Friends"
            val avatar = user.getString("avatar")
            val mainContact = user.getString("email")

            val courseResponse = UserResponse(
                userId = id,
                fullName = fullName,
                nickname = nickname,
                mainCategory = mainCategory,
                avatar = avatar,
                mainContact = mainContact
            )
            list.add(courseResponse)
        }
        return list
    }

    fun loadAllUsers(): List<UserResponse> {
        return loadData(R.raw.piko)
    }

    fun loadTopUsers(): List<UserResponse> {
        return loadData(R.raw.top_piko)
    }

    fun loadRecentUsers(): List<UserResponse> {
        return loadData(R.raw.recent_piko)
    }
}