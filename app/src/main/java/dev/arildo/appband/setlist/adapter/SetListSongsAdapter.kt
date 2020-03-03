package dev.arildo.appband.setlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arildojr.data.song.model.Song
import dev.arildo.appband.core.bindingadapter.BindableAdapter
import dev.arildo.appband.databinding.ItemSetListSetSongBinding

class SetListSongsAdapter(
    private var items: List<Song>,
    private val openSong: (Song) -> Unit
) : ListAdapter<Song, SetListSongsAdapter.ViewHolder>(PersonDiffCallback()),
    BindableAdapter<List<Song>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSetListSetSongBinding.inflate(inflater, parent, false)
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

    class ViewHolder(private val binding: ItemSetListSetSongBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song?, adapter: SetListSongsAdapter) {
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