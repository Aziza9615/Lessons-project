package com.example.firstapp.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstapp.R
import com.example.firstapp.model.Images
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfileAdapter(val listener: ClickListener): RecyclerView.Adapter<ProfileViewHolder>() {

    private var items = mutableListOf<Images>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item.url)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    fun addItems(items: MutableList<Images>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onItemClick(item: Images)
    }
}

class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(imageUrl: String?) {
        Glide.with(itemView.context).load(imageUrl).into(itemView.icon)
    }
}