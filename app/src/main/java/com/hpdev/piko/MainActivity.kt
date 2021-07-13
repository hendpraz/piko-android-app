package com.hpdev.piko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hpdev.piko.ui.contacts.ContactsFragment
import com.hpdev.piko.ui.history.HistoryFragment
import com.hpdev.piko.ui.home.HomeFragment
import com.hpdev.piko.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationBar)

        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        supportFragmentManager.beginTransaction().replace(R.id.frameContainer, HomeFragment()).commit()

        bottomNavigationView.setOnItemSelectedListener {
            val temp: Fragment = when (it.itemId) {
                R.id.mHome -> HomeFragment()
                R.id.mContacts -> ContactsFragment()
                R.id.mHistory -> HistoryFragment()
                R.id.mProfile -> ProfileFragment()
                else -> HomeFragment()
            }

            supportFragmentManager.beginTransaction().replace(R.id.frameContainer, temp).commit()
            true
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked plus button", Toast.LENGTH_SHORT).show()
        }
    }
}