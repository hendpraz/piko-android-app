package com.hpdev.piko.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hpdev.piko.R
import com.hpdev.piko.core.data.source.local.entity.UserEntity
import com.hpdev.piko.core.ui.ViewModelFactory
import com.hpdev.piko.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailUserViewModel: DetailUserViewModel

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val factory = ViewModelFactory.getInstance(this)
        detailUserViewModel = ViewModelProvider(this, factory)[DetailUserViewModel::class.java]

        val userEntity = intent.getParcelableExtra<UserEntity>(EXTRA_USER)

        if (userEntity != null) {
            showDetailUser(userEntity)
        }
    }

    private fun showDetailUser(userEntity: UserEntity) {
        with(binding) {
            imgShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                val shareBody = userEntity.fullName + " " + userEntity.mainContact
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "PIKO Contact"
                )
                intent.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(intent, "Share to.."))
            }

            Glide.with(this@DetailUserActivity)
                .load(userEntity.avatar)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                        .error(R.drawable.ic_baseline_error_24))
                .placeholder(R.drawable.add_contact)
                .into(imgAvatar)

            tvFullName.text = userEntity.fullName
            tvNickname.text = userEntity.nickname
            tvCategory.text = userEntity.mainCategory
            tvMainContact.text = userEntity.mainContact

            var statusFavorite = userEntity.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailUserViewModel.setFavoriteUser(userEntity, statusFavorite)
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