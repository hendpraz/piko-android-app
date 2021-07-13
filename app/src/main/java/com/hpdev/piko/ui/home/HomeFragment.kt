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
import com.hpdev.piko.ui.search.SearchActivity

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

        val contactSearch = fragmentHomeBinding.contactEt

        // if clicked, start search activity
        contactSearch.setOnClickListener {
            startSearchActivity()
        }

        // if focused, start search activity
        contactSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                startSearchActivity()
            }
        }

        // if there is no contact, show add contact card
        fragmentHomeBinding.addContactButton.setOnClickListener {
            Toast.makeText(context, "You clicked add contact button", Toast.LENGTH_SHORT).show()
        }

        // else, show favorites and recent contacts
    }

    private fun startSearchActivity() {
        val intent = Intent(context, SearchActivity::class.java)
        startActivity(intent)
    }
}