package com.hpdev.piko.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpdev.piko.data.UserEntity
import com.hpdev.piko.databinding.FragmentHomeContactsBinding
import com.hpdev.piko.ui.contacts.ContactsActivity
import com.hpdev.piko.ui.detail.DetailUserActivity
import com.hpdev.piko.ui.favorites.FavoritesActivity
import com.hpdev.piko.utils.generateEmptyFavorites
import com.hpdev.piko.utils.generateFavoriteUsers
import com.hpdev.piko.utils.generateRecentUsers

class HomeContactsFragment : Fragment(){
    private lateinit var homeContactsBinding: FragmentHomeContactsBinding
    private lateinit var favoritesAdapter: HomeFavoritesAdapter
    private lateinit var recentAdapter: HomeRecentAdapter

    private var favoriteUsers = mutableListOf<UserEntity>()
    private var recentUsers = mutableListOf<UserEntity>()

    companion object {
        const val EMPTY_FAVORITES_ID = -999
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        homeContactsBinding= FragmentHomeContactsBinding.inflate(layoutInflater, container, false)
        return homeContactsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoritesExist = true

        if (favoritesExist) {
            favoriteUsers = generateFavoriteUsers()

            homeContactsBinding.tvFavViewAll.setOnClickListener {
                val favoritesIntent = Intent(activity, FavoritesActivity::class.java)

                // start activity, to favorites list page
                startActivity(favoritesIntent)
            }

            favoritesAdapter = HomeFavoritesAdapter(favoriteUsers)
            favoritesAdapter.onItemClickCallback = (object : HomeFavoritesAdapter.OnItemClickCallback {
                override fun onItemClick(user: UserEntity) {
                    val detailIntent = Intent(activity, DetailUserActivity::class.java)
                        .apply {
                            putExtra(DetailUserActivity.EXTRA_USER_ID, user.id)
                        }

                    // start activity, to detail user page
                    startActivity(detailIntent)
                }
            })
        } else {
            this.favoriteUsers = generateEmptyFavorites()

            homeContactsBinding.tvFavViewAll.visibility = View.GONE

            favoritesAdapter = HomeFavoritesAdapter(favoriteUsers)
            favoritesAdapter.onItemClickCallback = (object : HomeFavoritesAdapter.OnItemClickCallback {
                override fun onItemClick(user: UserEntity) {
                    val contactsIntent = Intent(activity, ContactsActivity::class.java)

                    // start activity, to contacts list page
                    startActivity(contactsIntent)
                }
            })
        }

        with(homeContactsBinding.rvHomeContacts) {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = favoritesAdapter
        }

        val isRecentExist = true

        if (isRecentExist) {
            recentUsers = generateRecentUsers()

            recentAdapter = HomeRecentAdapter(recentUsers)

            recentAdapter.onItemClickCallback = (object : HomeRecentAdapter.OnItemClickCallback {
                override fun onItemClick(user: UserEntity) {
                    val detailIntent = Intent(activity, DetailUserActivity::class.java)
                        .apply {
                            putExtra(DetailUserActivity.EXTRA_USER_ID, user.id)
                        }

                    // start activity, to detail user page
                    startActivity(detailIntent)
                }
            })

            with(homeContactsBinding.rvHomeRecent) {
                layoutManager = LinearLayoutManager(activity)
                adapter = recentAdapter
            }
        } else {
            homeContactsBinding.tvRecentlyAdded.visibility = View.GONE
            homeContactsBinding.rvHomeRecent.visibility = View.GONE
        }
    }


}