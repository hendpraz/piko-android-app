package com.hpdev.piko.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hpdev.piko.R
import com.hpdev.piko.data.UserEntity

class HomeFavoritesAdapter(private val listUser: MutableList<UserEntity>) : RecyclerView.Adapter<HomeFavoritesAdapter.ListViewHolder>() {
    lateinit var onItemClickCallback: OnItemClickCallback

    fun setData(myListUser: MutableList<UserEntity>) {
        if (myListUser.size > 0) {
            this.listUser.clear()
        }

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
        holder.tvHorizontal.text = textToDisplay

        holder.tvHorizontal.setOnClickListener {
            onItemClickCallback.onItemClick(listUser[holder.adapterPosition])
        }

        holder.imageView.setOnClickListener {
            onItemClickCallback.onItemClick(listUser[holder.adapterPosition])
        }

        holder.overlayView.setOnClickListener {
            onItemClickCallback.onItemClick(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView : ImageView = itemView.findViewById(R.id.imageView)
        var tvHorizontal : TextView = itemView.findViewById(R.id.tvHorizontal)
        val overlayView : View = itemView.findViewById(R.id.overlayView)
    }

    interface OnItemClickCallback {
        fun onItemClick(user: UserEntity)
    }
}