package com.hpdev.piko.favorites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.ui.ContactsListAdapter
import com.hpdev.piko.core.ui.ViewModelFactory
import com.hpdev.piko.databinding.ActivityFavoritesBinding
import com.hpdev.piko.detail.DetailUserActivity

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactsAdapter = ContactsListAdapter()

        contactsAdapter.onItemClickCallback = (object : ContactsListAdapter.OnItemClickCallback {
            override fun onItemClick(user: User) {
                val detailIntent = Intent(this@FavoritesActivity, DetailUserActivity::class.java)
                detailIntent.putExtra(DetailUserActivity.EXTRA_USER, user)
                startActivity(detailIntent)
            }
        })

        val factory = ViewModelFactory.getInstance(this)
        favoritesViewModel = ViewModelProvider(this, factory)[FavoritesViewModel::class.java]

        favoritesViewModel.favoriteUsers.observe(this, {
            contactsAdapter.setData(it)
            binding.viewEmpty.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvContacts) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = contactsAdapter
        }
    }
}