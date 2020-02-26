package com.arildojr.appband.songlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arildojr.appband.core.bindingadapter.BindableAdapter
import com.arildojr.appband.databinding.ItemSongListBinding
import com.arildojr.data.song.model.Song

class SongsAdapter(
    private var items: List<Song>,
    private val openSong: (Song) -> Unit
) : ListAdapter<Song, SongsAdapter.ViewHolder>(PersonDiffCallback()),
    BindableAdapter<List<Song>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSongListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], this)

    override fun setData(data: List<Song>?) {
        this.items = data.orEmpty()
        notifyDataSetChanged()
    }

    fun onItemClicked(item: Song) {
        openSong(item)
    }

    class ViewHolder(private val binding: ItemSongListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song?, adapter: SongsAdapter) {
            binding.song = song
            binding.adapter = adapter
            binding.executePendingBindings()
        }
    }
}

class PersonDiffCallback : DiffUtil.ItemCallback<Song>() {

    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem == newItem
    }
}