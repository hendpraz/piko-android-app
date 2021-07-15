package com.hpdev.piko.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hpdev.piko.R
import com.hpdev.piko.databinding.FragmentHomeCardBinding

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
            Toast.makeText(context, "You clicked add contact button", Toast.LENGTH_SHORT).show()
        }
    }
}