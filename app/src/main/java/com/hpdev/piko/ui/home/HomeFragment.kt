package com.hpdev.piko.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

        contactSearch.setOnClickListener {
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }

        contactSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val intent = Intent(context, SearchActivity::class.java)
                startActivity(intent)
            }

            println(hasFocus)
        }
    }
}