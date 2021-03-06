package com.hpdev.piko.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.ui.ContactsListAdapter
import com.hpdev.piko.detail.DetailUserActivity
import com.hpdev.piko.favorite.databinding.ActivityFavoritesBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritesBinding
    private val favoritesViewModel: FavoritesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoritesModule)

        binding.imgBack.setOnClickListener {
            finish()
        }

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
            binding.viewEmpty.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvContacts) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = contactsAdapter
        }
    }
}