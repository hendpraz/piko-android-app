package com.hpdev.piko.favorites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpdev.piko.MyApplication
import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.ui.ContactsListAdapter
import com.hpdev.piko.core.ui.ViewModelFactory
import com.hpdev.piko.databinding.ActivityFavoritesBinding
import com.hpdev.piko.detail.DetailUserActivity
import javax.inject.Inject

class FavoritesActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory

    private val favoritesViewModel: FavoritesViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
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