package com.hpdev.piko.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpdev.piko.R
import com.hpdev.piko.core.data.source.local.entity.UserEntity
import com.hpdev.piko.databinding.FragmentHomeContactsBinding
import com.hpdev.piko.contacts.ContactsActivity
import com.hpdev.piko.core.data.Resource
import com.hpdev.piko.core.ui.ContactsHorizontalAdapter
import com.hpdev.piko.core.ui.ContactsListAdapter
import com.hpdev.piko.core.ui.ViewModelFactory
import com.hpdev.piko.core.utils.generateEmptyFavorites
import com.hpdev.piko.detail.DetailUserActivity
import com.hpdev.piko.favorites.FavoritesActivity

class HomeContactsFragment : Fragment(){
    private lateinit var binding: FragmentHomeContactsBinding
    private lateinit var favoritesAdapter: ContactsHorizontalAdapter
    private lateinit var recentAdapter: ContactsListAdapter

    private lateinit var homeViewModel: HomeViewModel

    companion object {
        const val EMPTY_FAVORITES_ID = -999
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding= FragmentHomeContactsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            recentAdapter = ContactsListAdapter()
            recentAdapter.onItemClickCallback = (object : ContactsListAdapter.OnItemClickCallback {
                override fun onItemClick(user: UserEntity) {
                    val detailIntent = Intent(activity, DetailUserActivity::class.java)
                    detailIntent.putExtra(DetailUserActivity.EXTRA_USER, user)
                    startActivity(detailIntent)
                }
            })

            favoritesAdapter = ContactsHorizontalAdapter()

            val factory = ViewModelFactory.getInstance(requireActivity())
            homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            homeViewModel.topFavorites.observe(viewLifecycleOwner, {
                if (it != null) {
                    if (it.isNotEmpty()) {
                        val favoriteUsers = it

                        favoritesAdapter.setData(favoriteUsers)
                        favoritesAdapter.onItemClickCallback = (object : ContactsHorizontalAdapter.OnItemClickCallback {
                            override fun onItemClick(user: UserEntity) {
                                val detailIntent = Intent(activity, DetailUserActivity::class.java)
                                detailIntent.putExtra(DetailUserActivity.EXTRA_USER, user)
                                startActivity(detailIntent)
                            }
                        })
                        binding.tvFavViewAll.setOnClickListener {
                            val favoritesIntent = Intent(activity, FavoritesActivity::class.java)
                            startActivity(favoritesIntent)
                        }
                    } else {
                        val favoriteUsers = generateEmptyFavorites()

                        binding.tvFavViewAll.visibility = View.GONE

                        favoritesAdapter.setData(favoriteUsers)
                        favoritesAdapter.onItemClickCallback = (object : ContactsHorizontalAdapter.OnItemClickCallback {
                            override fun onItemClick(user: UserEntity) {
                                val contactsIntent = Intent(activity, ContactsActivity::class.java)

                                // start activity, to contacts list page
                                startActivity(contactsIntent)
                            }
                        })
                    }
                }
            })

            with(binding.rvHomeContacts) {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = favoritesAdapter
            }

            homeViewModel.recentUsers.observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE

                            if (it.data != null) {
                                recentAdapter.setData(it.data)
                                if (it.data.isNotEmpty()) {
                                    binding.tvRecentlyAdded.visibility = View.VISIBLE
                                    binding.rvHomeRecent.visibility = View.VISIBLE
                                } else {
                                    binding.tvRecentlyAdded.visibility = View.GONE
                                    binding.rvHomeRecent.visibility = View.GONE
                                }
                            }
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = it.message ?: getString(R.string.something_went_wrong)
                        }
                    }

                    with(binding.rvHomeRecent) {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = recentAdapter
                    }
                } else {
                    binding.tvRecentlyAdded.visibility = View.GONE
                    binding.rvHomeRecent.visibility = View.GONE
                }
            })
        }
    }
}