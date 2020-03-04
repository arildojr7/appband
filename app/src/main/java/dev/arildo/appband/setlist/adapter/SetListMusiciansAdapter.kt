package dev.arildo.appband.setlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arildojr.data.musician.model.Musician
import com.arildojr.data.song.model.Song
import dev.arildo.appband.core.bindingadapter.BindableAdapter
import dev.arildo.appband.databinding.ItemMusicianBinding
import dev.arildo.appband.databinding.ItemSetListSetSongBinding

class SetListMusiciansAdapter(
    private var items: List<Musician>,
    private val openSong: (Musician) -> Unit
) : ListAdapter<Musician, SetListMusiciansAdapter.ViewHolder>(MusicianDiffCallback()),
    BindableAdapter<List<Musician>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMusicianBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], this)

    override fun setData(data: List<Musician>?) {
        this.items = data.orEmpty()
        notifyDataSetChanged()
    }

    fun onItemClicked(item: Musician) {
        openSong(item)
    }

    class ViewHolder(private val binding: ItemMusicianBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(musician: Musician?, adapter: SetListMusiciansAdapter) {
            binding.musician = musician
            binding.adapter = adapter
            binding.executePendingBindings()
        }
    }
}

class MusicianDiffCallback : DiffUtil.ItemCallback<Musician>() {

    override fun areItemsTheSame(oldItem: Musician, newItem: Musician): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Musician, newItem: Musician): Boolean {
        return oldItem == newItem
    }
}