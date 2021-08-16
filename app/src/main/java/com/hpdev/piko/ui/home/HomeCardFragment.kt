package com.hpdev.piko.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hpdev.piko.R
import com.hpdev.piko.databinding.FragmentHomeCardBinding
import com.hpdev.piko.ui.add_contact.AddContactActivity
import com.hpdev.piko.ui.contacts.ContactsActivity

class HomeCardFragment : Fragment() {

    private lateinit var homeCardBinding: FragmentHomeCardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeCardBinding = FragmentHomeCardBinding.inflate(layoutInflater, container, false)
        return homeCardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeCardBinding.addContactButton.setOnClickListener {
            val addContactIntent = Intent(activity, AddContactActivity::class.java)

            // start activity, to add contact list page
            startActivity(addContactIntent)
        }
    }
}