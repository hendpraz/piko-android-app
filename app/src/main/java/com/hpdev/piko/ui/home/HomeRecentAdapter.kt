package com.hpdev.piko.ui.home

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
import com.hpdev.piko.data.UserEntity

class HomeRecentAdapter(private val listUser: MutableList<UserEntity>) : RecyclerView.Adapter<HomeRecentAdapter.ListViewHolder>() {
    lateinit var onItemClickCallback: OnItemClickCallback

    fun setData(myListUser: MutableList<UserEntity>) {
        if (myListUser.size > 0) {
            this.listUser.clear()
        }

        this.listUser.addAll(myListUser)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_home_item2, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                    .error(R.drawable.ic_baseline_error_24))
            .placeholder(R.drawable.add_contact)
            .into(holder.imageView)

        var textToDisplay = user.nickname
        if (user.nickname.length > 8) {
            textToDisplay = user.nickname.take(8) + "..."
        }
        holder.tvNickname.text = textToDisplay
        holder.tvCategory.text = user.mainCategory
        holder.tvMainContact.text = user.mainContact

        holder.cardView.setOnClickListener {
            onItemClickCallback.onItemClick(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView : CardView = itemView.findViewById(R.id.cvItemRecent)
        var imageView : ImageView = itemView.findViewById(R.id.imgPoster)
        var tvNickname : TextView = itemView.findViewById(R.id.tvNickname)
        var tvCategory : TextView = itemView.findViewById(R.id.tvCategory)
        var tvMainContact : TextView = itemView.findViewById(R.id.tvMainContact)
    }

    interface OnItemClickCallback {
        fun onItemClick(user: UserEntity)
    }
}