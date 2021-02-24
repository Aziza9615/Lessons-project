package com.example.firstapp.ui.image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstapp.R
import com.example.firstapp.model.Images
import kotlinx.android.synthetic.main.fragment_image_carousel.view.*

class ImagePublicationAdapter() : RecyclerView.Adapter<ImagePublicationViewHolder>() {

    private var urls: MutableList<Images> = mutableListOf<Images>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePublicationViewHolder {
        return ImagePublicationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return urls.count()
    }

    override fun onBindViewHolder(holder: ImagePublicationViewHolder, position: Int) {
        val item = urls.get(position)
        holder.bind(item)

    }

    fun addItems(items: MutableList<Images>) {
        urls = items
        notifyDataSetChanged()
    }
}

class ImagePublicationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: Images?) {
        Glide.with(itemView.image_iv.context).load(data?.url).into(itemView.image_iv)
    }
}