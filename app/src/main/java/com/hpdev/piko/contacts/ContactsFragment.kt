package com.hpdev.piko.contacts

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hpdev.piko.R
import com.hpdev.piko.core.data.Resource
import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.core.ui.ContactsListAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import com.hpdev.piko.databinding.FragmentContactsBinding
import com.hpdev.piko.detail.DetailUserActivity

class ContactsFragment : Fragment() {
    private lateinit var binding: FragmentContactsBinding
    private lateinit var topUsersAdapter: ContactsListAdapter
    private val contactsViewModel: ContactsViewModel by viewModel()
    private var sortBy = SORT_BY_NAME

    companion object {
        const val SORT_BY_NAME = "Name"
        const val SORT_BY_CATEGORY = "Category"
    }

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

            val items = arrayOf(SORT_BY_NAME, SORT_BY_CATEGORY)

            binding.tvSort.setOnClickListener {
                context?.let { it1 ->
                    MaterialAlertDialogBuilder(it1)
                        .setTitle("Sort by")
                        .setItems(items) { _, which ->
                            sortBy = items[which]

                            if (sortBy == SORT_BY_NAME) {
                                topUsersAdapter.setData(topUsersAdapter.getData().sortedWith(compareBy { it.fullName }))
                            } else {
                                topUsersAdapter.setData(topUsersAdapter.getData().sortedWith(compareBy { it.mainCategory }))
                            }
                        }
                        .show()
                }
            }
        }

        contactsViewModel.allUsers.observe(viewLifecycleOwner, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE

                        if (it.data != null) {
                            topUsersAdapter.setData(it.data)
                            if (it.data!!.isNotEmpty()) {
                                binding.viewEmpty.root.visibility = View.GONE
                            } else {
                                binding.viewEmpty.root.visibility = View.VISIBLE
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