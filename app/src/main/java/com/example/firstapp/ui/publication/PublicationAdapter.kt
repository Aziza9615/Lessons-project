package com.example.firstapp.ui.publication

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstapp.R
import com.example.firstapp.model.Comment
import com.example.firstapp.model.Images
import com.example.firstapp.model.Publication
import com.example.firstapp.ui.comment.CommentsPublicationAdapter
import com.example.firstapp.ui.image.ImagePublicationAdapter
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator
import kotlinx.android.synthetic.main.item_main.view.*

class PublicationAdapter(private val listener: ClickListener, private val activity: Activity) : RecyclerView.Adapter<PublicationViewHolder>() {
    
    private var items = mutableListOf<Publication>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationViewHolder {
        return PublicationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        )
    }
    
    override fun getItemCount(): Int {
        return items.count()
    }
    
    override fun onBindViewHolder(holder: PublicationViewHolder, position: Int) {
        val item = items.get(position)
        holder.bind(item, activity)
        holder.itemView.favorite_btn.setOnClickListener {
            listener.onFavoriteClick(item, position)
            holder.itemView.favorite_btn.setImageResource(getFavoriteIcon(item.isFavorite))
        }
        holder.itemView.comment_btn.setOnClickListener {
            listener.onCommentClick(item)
        }
        holder.itemView.direct_btn.setOnClickListener {
            listener.onDirectClick(item)
        }
    }
    
    fun addItems(items: MutableList<Publication>) {
        this.items = items
        notifyDataSetChanged()
    }
    
    fun updateItem(position: Int) {
        notifyItemChanged(position)
    }
    
    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRangeRemoved(position, itemCount)
    }
    
    interface ClickListener {
        fun onFavoriteClick(item: Publication, position: Int)
        fun onCommentClick(item: Publication)
        fun onDirectClick(item: Publication)
    }
}

class PublicationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Publication, activity: Activity) {
        Glide.with(itemView.context).load(item.icon).placeholder(R.drawable.ic_people).into(itemView.icon_civ)
        itemView.name_tv.text = item.name
        itemView.count_of_favorite_tv.text = "${item.countOfFavorite}"
        if (item.countOfFavorite == 0) itemView.count_of_favorite_tv.visibility = View.GONE
        else itemView.count_of_favorite_tv.visibility = View.VISIBLE
        itemView.favorite_btn.setImageResource(getFavoriteIcon(item.isFavorite))
        setupImagesRecyclerView(item.images, itemView.images_rv, itemView.rv_pi)
        setupCommentsRecyclerView(item.comments, itemView.comments_rv)
    }

    //protected - метод с параметром доступа виден для других классов только внутри родительской папки
    //private - метод с параметром доступа виден для других классов только внутри класс
    //public - метод с параметром доступа виден всем
    //internal - метод с параметром доступа виден для других только для родительского модуля

    private fun setupCommentsRecyclerView(items: MutableList<Comment>?, recyclerView: RecyclerView) {
        val adapter = CommentsPublicationAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(recyclerView.context)
            this.adapter = adapter
        }
        items?.let { adapter.addItems(it) }
    }
}

fun setupImagesRecyclerView(items: MutableList<Images>?, recyclerView: RecyclerView, pagerIndicator: IndefinitePagerIndicator) {
    val adapter = ImagePublicationAdapter()
    val snapHelper = PagerSnapHelper()
    recyclerView.apply {
        layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
        this.adapter = adapter
        this.onFlingListener = null
        snapHelper.attachToRecyclerView(this)
        pagerIndicator.attachToRecyclerView(this)
    }
    items?.let { adapter.addItems(it) }
}

private fun getFavoriteIcon(state: Boolean): Int {
    return if (state) R.drawable.ic_favorite
    else R.drawable.ic_unfavorite
}

 //View.VISIBLE = ОТОБРАЖАЕТСЯ
// View.GONE = неотображается и Text view не занимает места
//View.INVISIBLE - НЕ ОТОБРАЖАЕТСЯ НО text view ЗАНИМАЕТ МЕСТА



