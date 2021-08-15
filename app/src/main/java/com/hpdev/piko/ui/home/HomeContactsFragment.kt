package com.hpdev.piko.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpdev.piko.R
import com.hpdev.piko.data.UserEntity
import com.hpdev.piko.databinding.FragmentHomeContactsBinding
import com.hpdev.piko.ui.detail.DetailUserActivity

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
            favoriteUsers = generateDummyUsers()

            homeContactsBinding.tvFavViewAll.setOnClickListener {
                Toast.makeText(activity, "Go to Favorites page...", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(activity, "Go to AddFavorite page...", Toast.LENGTH_SHORT).show()
                }
            })
        }

        with(homeContactsBinding.rvHomeContacts) {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = favoritesAdapter
        }

        val isRecentExist = true

        if (isRecentExist) {
            recentUsers = generateDummyUsers()

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

    private fun generateEmptyFavorites() : MutableList<UserEntity> {
        val listUser = mutableListOf<UserEntity>()

        listUser.add(
            UserEntity(
                id = EMPTY_FAVORITES_ID,
                fullName = "",
                nickname = "",
                mainCategory = "",
                avatar = R.drawable.add_favorite,
                mainContact = ""
            )
        )

        return listUser
    }

    private fun generateDummyUsers() : MutableList<UserEntity> {
        val listUser = mutableListOf<UserEntity>()

        listUser.add(
            UserEntity(
                id = 0,
                fullName = "Hendry Prasetya",
                nickname = "Hendry",
                mainCategory = "College Friends",
                avatar = R.drawable.add_contact,
                mainContact = "@hendryprasetyaa (IG)"
            )
        )

        listUser.add(
            UserEntity(
                id = 1,
                fullName = "Vian Aldi",
                nickname = "Bian Barudi",
                mainCategory = "HighSchool Friends",
                avatar = R.drawable.add_contact,
                mainContact = "@vian_aldi (IG)"
            )
        )

        listUser.add(
            UserEntity(
                id = 2,
                fullName = "Mr. Pambudi Luhut",
                nickname = "Pak Pam",
                mainCategory = "Lecturer",
                avatar = R.drawable.add_contact,
                mainContact = "@pambudi_lht (IG)"
            )
        )

        listUser.add(
            UserEntity(
                id = 3,
                fullName = "Khairul Akmal",
                nickname = "BAKWO CAK KHAIRUL",
                mainCategory = "Family",
                avatar = R.drawable.add_contact,
                mainContact = "0812-2345-2312 (WA)"
            )
        )

        listUser.add(
            UserEntity(
                id = 4,
                fullName = "Marechiyo Omaeda",
                nickname = "Omaeda",
                mainCategory = "Others",
                avatar = R.drawable.add_contact,
                mainContact = "0814-2325-2312 (WA)"
            )
        )

        return listUser
    }
}