package com.hpdev.piko.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hpdev.piko.R

class DetailUserActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER_ID = "extra_user_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
    }
}