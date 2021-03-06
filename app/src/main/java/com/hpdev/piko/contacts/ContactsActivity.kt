package com.hpdev.piko.contacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpdev.piko.R
import com.hpdev.piko.core.data.Resource
import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.ui.ContactsListAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import com.hpdev.piko.databinding.ActivityContactsBinding
import com.hpdev.piko.detail.DetailUserActivity

class ContactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private lateinit var allContacts: ContactsListAdapter

    private val contactsViewModel: ContactsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.imgBack.setOnClickListener {
            finish()
        }

        allContacts = ContactsListAdapter()
        allContacts.onItemClickCallback = (object : ContactsListAdapter.OnItemClickCallback {
            override fun onItemClick(user: User) {
                val detailIntent = Intent(this@ContactsActivity, DetailUserActivity::class.java)
                detailIntent.putExtra(DetailUserActivity.EXTRA_USER, user)
                startActivity(detailIntent)
            }
        })

        contactsViewModel.allUsers.observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        allContacts.setData(it.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = it.message ?: getString(R.string.something_went_wrong)
                    }
                }
            }
        })

        with(binding.rvContacts) {
            layoutManager = LinearLayoutManager(this@ContactsActivity)
            adapter = allContacts
        }
    }
}