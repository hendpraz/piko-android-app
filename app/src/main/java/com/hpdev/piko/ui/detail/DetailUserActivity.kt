package com.hpdev.piko.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hpdev.piko.R
import com.hpdev.piko.databinding.ActivityDetailUserBinding
import com.hpdev.piko.utils.getUserById


class DetailUserActivity : AppCompatActivity() {
    private lateinit var detailUserBinding: ActivityDetailUserBinding

    companion object {
        const val EXTRA_USER_ID = "extra_user_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        val view = detailUserBinding.root
        setContentView(view)

        val bundle = intent.extras
        if (bundle != null) {
            val userEntity = getUserById(bundle.getInt(EXTRA_USER_ID))

            with(detailUserBinding) {
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
            }
        }

    }
}