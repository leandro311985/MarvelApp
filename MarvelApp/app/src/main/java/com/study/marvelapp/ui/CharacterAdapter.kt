package com.study.marvelapp.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.study.marvelapp.R
import com.study.marvelapp.model.CharacterResults
import kotlinx.android.synthetic.main.character_list_item.view.*

class CharacterAdapter(val itemClickListener: (CharacterResults) -> Unit):
    ListAdapter<CharacterResults, CharacterAdapter.ViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            itemClickListener(getItem(position))
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(results: CharacterResults) {
            itemView.character_name.text = results.name

            Glide.with(itemView.character_thumbnail.context)
                .load(results.thumbnail.path + "/portrait_incredible." + results.thumbnail.extension)
                .into(itemView.character_thumbnail)
        }
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<CharacterResults>() {
        override fun areItemsTheSame(oldItem: CharacterResults, newItem: CharacterResults): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: CharacterResults, newItem: CharacterResults): Boolean =
            oldItem == newItem
    }
}