package com.hpdev.piko.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hpdev.piko.R
import com.hpdev.piko.core.domain.model.User

class ContactsHorizontalAdapter : RecyclerView.Adapter<ContactsHorizontalAdapter.ListViewHolder>() {
    private val listUser = ArrayList<User>()
    lateinit var onItemClickCallback: OnItemClickCallback

    fun setData(myListUser: List<User>?) {
        if (myListUser == null) return
        this.listUser.clear()
        this.listUser.addAll(myListUser)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_home_item1, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]

        Glide.with(holder.itemView.context)
            .load(if (user.avatar == "add") R.drawable.add_favorite else user.avatar)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                    .error(R.drawable.ic_baseline_error_24))
            .placeholder(R.drawable.add_contact)
            .into(holder.imageView)

        var textToDisplay = user.nickname
        if (user.nickname.length > 8) {
            textToDisplay = user.nickname.take(8) + "..."
        }
        holder.tvHorizontal.text = textToDisplay

        holder.cardView.setOnClickListener {
            onItemClickCallback.onItemClick(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount() = listUser.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.cvFavorites)
        var imageView : ImageView = itemView.findViewById(R.id.imgAvatar)
        var tvHorizontal : TextView = itemView.findViewById(R.id.tvHorizontal)
    }

    interface OnItemClickCallback {
        fun onItemClick(user: User)
    }
}