package g54490.mobg5.sharestudent.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import g54490.mobg5.sharestudent.databinding.ListeItemPublicationBinding
import g54490.mobg5.sharestudent.model.Publication

class PublicationAdapter(private val clickListener:PublicationListener):androidx.recyclerview.widget.ListAdapter<Publication,PublicationAdapter.ViewHolder>(PublicationDiffCallback()){

    class ViewHolder private constructor(val binding: ListeItemPublicationBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Publication, clickListener: PublicationListener) {
            binding.publication = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListeItemPublicationBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }
}
class PublicationListener(val clickListener: (id: String) -> Unit) {
    fun onClick(pub: Publication) = clickListener(pub.id)
}

class PublicationDiffCallback : DiffUtil.ItemCallback<Publication>() {
    override fun areItemsTheSame(oldItem: Publication, newItem: Publication): Boolean {
        return oldItem.id== newItem.id
    }
    override fun areContentsTheSame(oldItem: Publication, newItem: Publication): Boolean {
        return oldItem == newItem
    }
}
