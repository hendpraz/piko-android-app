package com.hpdev.piko.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.hpdev.piko.R
import com.hpdev.piko.databinding.FragmentHomeBinding
import com.hpdev.piko.ui.contacts.ContactsActivity
import com.hpdev.piko.ui.search.SearchActivity
import com.hpdev.piko.ui.settings.SettingsActivity

class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set up search edit text
        setUpSearchEditText()

        val isContactsExist = true

        // if a contact exists, show favorites and recent contacts
        if (isContactsExist) {
            val childFragment = HomeContactsFragment()
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.frameContainerHome, childFragment).commit()

        } else { // else, show add contact card
            val childFragment = HomeCardFragment()
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.frameContainerHome, childFragment).commit()
        }

        // settings button
        fragmentHomeBinding.imgSetting.setOnClickListener {
            val settingsIntent = Intent(activity, SettingsActivity::class.java)

            // start activity, to settings page
            startActivity(settingsIntent)
        }
    }

    private fun setUpSearchEditText() {
        val contactSearch = fragmentHomeBinding.contactEt

        // if clicked, start search activity
        contactSearch.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }

        // if focused, start search activity
        contactSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val intent = Intent(context, SearchActivity::class.java)
                startActivity(intent)
            }
        }
    }
}