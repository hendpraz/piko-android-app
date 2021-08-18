package com.hpdev.piko.ui.contacts

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpdev.piko.data.UserEntity
import com.hpdev.piko.databinding.FragmentContactsBinding
import com.hpdev.piko.ui.detail.DetailUserActivity
import com.hpdev.piko.ui.favorites.FavoritesActivity
import com.hpdev.piko.ui.home.HomeRecentAdapter
import com.hpdev.piko.utils.getTopRecentUsers

class ContactsFragment : Fragment() {
    private lateinit var fragmentContactsBinding: FragmentContactsBinding
    private lateinit var topRecentAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentContactsBinding = FragmentContactsBinding.inflate(layoutInflater, container, false)
        return fragmentContactsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isContactExist = true

        if (isContactExist) {
            val topRecentUsers = getTopRecentUsers()
            val topRecentAdapter = ContactAdapter(topRecentUsers)

            topRecentAdapter.onItemClickCallback = (object : ContactAdapter.OnItemClickCallback {
                override fun onItemClick(user: UserEntity) {
                    val detailIntent = Intent(activity, DetailUserActivity::class.java)
                        .apply {
                            putExtra(DetailUserActivity.EXTRA_USER_ID, user.id)
                        }

                    // start activity, to detail user page
                    startActivity(detailIntent)
                }
            })

            fragmentContactsBinding.tvViewAll.setOnClickListener {
                val contactsIntent = Intent(activity, ContactsActivity::class.java)

                // start activity, to contacts activity
                startActivity(contactsIntent)
            }

            with(fragmentContactsBinding.rvContacts) {
                layoutManager = LinearLayoutManager(activity)
                adapter = topRecentAdapter
            }
        }
    }
}