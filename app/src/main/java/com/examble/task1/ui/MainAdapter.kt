package com.examble.task1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examble.task1.databinding.ItemRowBinding
import com.examble.task1.ui.test.Al_Baghdadia

class MainAdapter(private val alBaghdadia: Al_Baghdadia): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemRowBinding?): RecyclerView.ViewHolder(binding?.root!!)

    override fun getItemCount():Int{

        return alBaghdadia.posts.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: ItemRowBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val postItem = alBaghdadia.posts.get(position)
        holder.binding?.title?.text = postItem.post_title
        holder.binding?.category?.text = postItem.category.joinToString { it }
        holder.binding?.date?.text = postItem.post_date.split(" ").first()
        val image = holder.binding?.imageView
        Glide.with(holder.itemView.context).load(postItem.img_url).into(image!!)

    }

}
