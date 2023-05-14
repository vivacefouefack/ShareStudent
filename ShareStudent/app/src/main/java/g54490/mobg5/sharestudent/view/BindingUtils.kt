package g54490.mobg5.sharestudent.view

import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.model.Repository
import java.io.File

@BindingAdapter("pubImage")
fun ImageView.publicationImage( item: Publication?) {
    item?.let {
        val storage=Repository.getStorage().child(item.image)
        val localFile= File.createTempFile("tempImage","jpg")
        storage.getFile(localFile).addOnSuccessListener {
            val bitmap= BitmapFactory.decodeFile(localFile.absolutePath)
            setImageBitmap(bitmap)
        }.addOnFailureListener{
            Log.i("image error","error to get image from firebase")
        }
    }
}

@BindingAdapter("pubTitle")
fun TextView.setPubTitle(item: Publication?) {
    item?.let {
        text = item.title
    }
}