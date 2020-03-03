package dev.arildo.appband.setlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.arildo.appband.core.bindingadapter.BindableAdapter
import dev.arildo.appband.databinding.ItemSetListBinding
import com.arildojr.data.setlist.model.SetList

class SetListAdapter(
    private var items: List<SetList>,
    private val openSetList: (SetList, Int) -> Unit
) : ListAdapter<SetList, SetListAdapter.ViewHolder>(SetListDiffCallback()),
    BindableAdapter<List<SetList>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSetListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], this, position)

    override fun setData(data: List<SetList>?) {
        this.items = data.orEmpty()
        notifyDataSetChanged()
    }

    fun onItemClicked(item: SetList, position: Int) {
        openSetList(item, position)
    }

    class ViewHolder(private val binding: ItemSetListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(setList: SetList?, adapter: SetListAdapter, position: Int) {
            binding.setList = setList
            binding.adapter = adapter
            binding.setListNumber = position + 1
            binding.executePendingBindings()
        }
    }
}

class SetListDiffCallback : DiffUtil.ItemCallback<SetList>() {

    override fun areItemsTheSame(oldItem: SetList, newItem: SetList): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SetList, newItem: SetList): Boolean {
        return oldItem == newItem
    }
}