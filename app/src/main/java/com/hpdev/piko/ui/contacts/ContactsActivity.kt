package com.hpdev.piko.ui.contacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpdev.piko.R
import com.hpdev.piko.data.UserEntity
import com.hpdev.piko.databinding.ActivityContactsBinding
import com.hpdev.piko.ui.detail.DetailUserActivity
import com.hpdev.piko.utils.getAllUsers
import com.hpdev.piko.utils.getTopRecentUsers

class ContactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        val allUsers = getAllUsers()
        val topRecentAdapter = ContactAdapter(allUsers)

        topRecentAdapter.onItemClickCallback = (object : ContactAdapter.OnItemClickCallback {
            override fun onItemClick(user: UserEntity) {
                val detailIntent = Intent(this@ContactsActivity, DetailUserActivity::class.java)
                    .apply {
                        putExtra(DetailUserActivity.EXTRA_USER_ID, user.id)
                    }

                // start activity, to detail user page
                startActivity(detailIntent)
            }
        })

        with(binding.rvContacts) {
            layoutManager = LinearLayoutManager(this@ContactsActivity)
            adapter = topRecentAdapter
        }
    }
}