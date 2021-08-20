package com.hpdev.piko.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hpdev.piko.core.data.Resource
import com.hpdev.piko.history.databinding.ActivityHistoryBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class HistoryActivity : AppCompatActivity() {
    private val historyViewModel: HistoryViewModel by viewModel()
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(historyModule)

        supportActionBar?.title = "PIKO Contact History"

        getUsersData()
    }

    private fun getUsersData() {
        historyViewModel.users.observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvHistory.text = "You added ${it.data?.get(0)?.fullName} on ${it.data?.get(0)?.dateAdded}"
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = it.message
                    }
                }
            }
        })
    }
}