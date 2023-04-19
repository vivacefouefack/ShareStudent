package g54490.mobg5.sharestudent.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.model.Publication

class PublicationAdapter:RecyclerView.Adapter<PublicationAdapter.ViewHolder>() {

    var data =  listOf<Publication>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }


    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.publication_title)
        val image:ImageView=itemView.findViewById(R.id.publication_image)

        fun bind(item: Publication) {
            //val res = itemView.context.resources
            title.text=item.title
            image.setImageResource(when (item.image) {
                0 -> R.drawable.disk1
                1 -> R.drawable.livre1
                2 -> R.drawable.big
                3 -> R.drawable.livre4
                4 -> R.drawable.usb
                5 -> R.drawable.livre2
                6 -> R.drawable.usb1
                7 -> R.drawable.livre3
                else -> R.drawable.account
            })
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.liste_item_publication, parent, false)
                return ViewHolder(view)
            }
        }
    }

}