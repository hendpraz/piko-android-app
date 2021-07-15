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
import com.hpdev.piko.databinding.FragmentHomeCardBinding
import com.hpdev.piko.databinding.FragmentHomeContactsBinding
import com.hpdev.piko.ui.detail.DetailUserActivity

class HomeContactsFragment : Fragment(){
    private lateinit var homeContactsBinding: FragmentHomeContactsBinding
    private lateinit var userAdapter: HomeUserAdapter
    private var listUser = mutableListOf<UserEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        homeContactsBinding= FragmentHomeContactsBinding.inflate(layoutInflater, container, false)
        return homeContactsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateDummyUsers()

        userAdapter = HomeUserAdapter(listUser)
        userAdapter.onItemClickCallback = (object : HomeUserAdapter.OnItemClickCallback {
            override fun onItemClick(user: UserEntity) {
                val detailIntent = Intent(activity, DetailUserActivity::class.java)
                    .apply {
                        putExtra(DetailUserActivity.EXTRA_USER_ID, user.id)
                    }

                // start activity, to detail user page
                startActivity(detailIntent)
            }
        })

        with(homeContactsBinding.rvHomeContacts) {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = userAdapter
        }
    }

    private fun generateDummyUsers() {
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
    }
}