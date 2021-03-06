package com.hpdev.piko.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hpdev.piko.R
import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.databinding.ActivityDetailUserBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private val detailUserViewModel: DetailUserViewModel by viewModel()

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val user = intent.getParcelableExtra<User>(EXTRA_USER)

        if (user != null) {
            showDetailUser(user)
        }
    }

    private fun showDetailUser(user: User) {
        with(binding) {
            imgShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                val shareBody = user.fullName + " " + user.mainContact
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "PIKO Contact"
                )
                intent.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(intent, "Share to.."))
            }

            imgBack.setOnClickListener {
                finish()
            }

            Glide.with(this@DetailUserActivity)
                .load(user.avatar)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                        .error(R.drawable.ic_baseline_error_24))
                .placeholder(R.drawable.add_contact)
                .into(imgAvatar)

            tvFullName.text = user.fullName
            tvNickname.text = user.nickname
            tvCategory.text = user.mainCategory
            tvMainContact.text = user.mainContact

            var statusFavorite = user.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailUserViewModel.setFavoriteUser(user, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24_white))
        }
    }
}