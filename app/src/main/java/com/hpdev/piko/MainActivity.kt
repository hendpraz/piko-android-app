package com.hpdev.piko

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hpdev.piko.add_contact.AddContactActivity
import com.hpdev.piko.contacts.ContactsFragment
import com.hpdev.piko.home.HomeFragment
import com.hpdev.piko.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationBar)

        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        supportFragmentManager.beginTransaction().replace(R.id.frameContainer, HomeFragment()).commit()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.mHistory) {
                val uri = Uri.parse("piko://history")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            } else {
                val temp: Fragment = when (it.itemId) {
                    R.id.mHome -> HomeFragment()
                    R.id.mContacts -> ContactsFragment()
                    R.id.mProfile -> ProfileFragment()
                    else -> HomeFragment()
                }

                supportFragmentManager.beginTransaction().replace(R.id.frameContainer, temp).commit()
            }
            true
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val addContactIntent = Intent(this@MainActivity, AddContactActivity::class.java)

            // start activity, to add contact list page
            startActivity(addContactIntent)
        }
    }
}