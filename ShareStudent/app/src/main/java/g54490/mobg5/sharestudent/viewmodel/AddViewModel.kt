package g54490.mobg5.sharestudent.viewmodel

import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.model.Repository
import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class AddViewModel(private val context: Context):ViewModel() {

    private var userlog: String
        get() = Repository.getUsername()

    private var _title = MutableLiveData<CharSequence>()
    private val title: LiveData<CharSequence>
        get() = _title


    private var _description = MutableLiveData<CharSequence>()
    private val description: LiveData<CharSequence>
        get() = _description

    private var _publishPress = MutableLiveData<Boolean?>()
    val publishPress: LiveData<Boolean?>
        get() = _publishPress

    private var _cameraPress = MutableLiveData<Boolean?>()
    val cameraPress: LiveData<Boolean?>
        get() = _cameraPress

    private var _galleryPress = MutableLiveData<Boolean?>()
    val galleryPress: LiveData<Boolean?>
        get() = _galleryPress

    private var imageName=System.currentTimeMillis().toString()+".jpg"
    var captureImageUri: Uri=Uri.EMPTY

    init {
        userlog= Repository.getUsername()
        _title.value =""
        _description.value =""
        _publishPress.value=null
        _galleryPress.value=null
        _cameraPress.value=null
    }

    fun titleText(s: CharSequence, start: Int, before: Int, count: Int) {
        _title.value = s.toString()
    }

    fun descriptionText(s: CharSequence, start: Int, before: Int, count: Int) {
        _description.value = s.toString()
    }

    fun checkDataAndCreatePublication(){
        if(_title.value.toString().isNotEmpty() && _description.value.toString().isNotEmpty()){
            try {
               Repository.createPublication(Publication("","images/"+imageName,_title.value.toString(),_description.value.toString(),userlog))
            }catch (e:Exception){
                e.message
            }
            this._publishPress.value=true
        }else{
            _publishPress.value=false
        }
    }

    fun getImageName(): String {
        return imageName
    }

    fun setImageName(name:String){
        imageName=name
    }

    fun setCameraPress(){
        _cameraPress.value=true
    }
    fun setGalleryPress(){
        _galleryPress.value=true
    }

    fun saveMediaToStorage(bitmap: Bitmap) {
        setImageName(System.currentTimeMillis().toString()+".jpg")
        val filename=getImageName()
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            context?.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                captureImageUri=imageUri!!
                fos = imageUri.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
    }

}