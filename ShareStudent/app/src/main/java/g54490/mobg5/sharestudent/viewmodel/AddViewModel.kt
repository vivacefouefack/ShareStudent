package g54490.mobg5.sharestudent.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.model.Repository

class AddViewModel:ViewModel() {

    private var _userlog = MutableLiveData<String>()
    var userlog: String
        get() = Repository.getUsername()

    private var _image = MutableLiveData<Int>()
    private val image: LiveData<Int>
        get() = _image

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

    private var _takePicture = MutableLiveData<Boolean?>()
    val takePicture: LiveData<Boolean?>
        get() = _takePicture

    private var imageName=System.currentTimeMillis().toString()+".jpg"

    init {
        userlog= Repository.getUsername()
        _image.value =1
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
            //imageName=System.currentTimeMillis().toString()
            Log.i("insert",_title.value.toString())
            Log.i("insert",_description.value.toString())
            Log.i("insert",Repository.getUsername())
            Log.i("insert",imageName)
            try {
               Repository.createPublication(Publication("","images/"+imageName,_title.value.toString(),_description.value.toString(),userlog))
            }catch (e:Exception){

            }
            this._publishPress.value=true
        }else{
            _publishPress.value=false
        }
    }

    fun setTakePicture(){
        _takePicture.value=false
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

}