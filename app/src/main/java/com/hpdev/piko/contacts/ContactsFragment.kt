package com.hpdev.piko.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hpdev.piko.MyApplication
import com.hpdev.piko.R
import com.hpdev.piko.core.data.Resource
import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.ui.ContactsListAdapter
import com.hpdev.piko.core.ui.ViewModelFactory
import com.hpdev.piko.databinding.FragmentContactsBinding
import com.hpdev.piko.detail.DetailUserActivity
import javax.inject.Inject

class ContactsFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory

    private val contactsViewModel: ContactsViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    private lateinit var binding: FragmentContactsBinding
    private lateinit var topUsersAdapter: ContactsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentContactsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            topUsersAdapter = ContactsListAdapter()

            topUsersAdapter.onItemClickCallback = (object : ContactsListAdapter.OnItemClickCallback {
                override fun onItemClick(user: User) {
                    val detailIntent = Intent(activity, DetailUserActivity::class.java)
                    detailIntent.putExtra(DetailUserActivity.EXTRA_USER, user)
                    startActivity(detailIntent)
                }
            })

            binding.tvViewAll.setOnClickListener {
                val contactsIntent = Intent(activity, ContactsActivity::class.java)

                // start activity, to contacts activity
                startActivity(contactsIntent)
            }

            contactsViewModel.topUsers.observe(viewLifecycleOwner, {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE

                            if (it.data != null) {
                                topUsersAdapter.setData(it.data)
                                if (it.data.isNotEmpty()) {
                                    binding.viewEmpty.root.visibility = View.GONE
                                    binding.tvViewAll.visibility = View.VISIBLE
                                } else {
                                    binding.viewEmpty.root.visibility = View.VISIBLE
                                    binding.tvViewAll.visibility = View.GONE
                                }
                            }
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = it.message ?: getString(R.string.something_went_wrong)
                        }
                    }
                }
            })

            with(binding.rvContacts) {
                layoutManager = LinearLayoutManager(activity)
                adapter = topUsersAdapter
            }
        }
    }
}