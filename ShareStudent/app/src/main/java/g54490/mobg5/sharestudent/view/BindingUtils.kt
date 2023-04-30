package g54490.mobg5.sharestudent.view

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import g54490.mobg5.sharestudent.R
import g54490.mobg5.sharestudent.model.Publication

@BindingAdapter("pubImage")
fun ImageView.publicationImage( item: Publication?) {
    item?.let {
        setImageResource(when (item.image) {
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
}

@BindingAdapter("pubTitle")
fun TextView.setPubTitle(item: Publication?) {
    item?.let {
        text = item.title
    }
}