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

    private var _publishButton = MutableLiveData<Boolean?>()
    val publishButton: LiveData<Boolean?>
        get() = _publishButton

    private var _takePicture = MutableLiveData<Boolean?>()
    val takePicture: LiveData<Boolean?>
        get() = _takePicture

    init {
        userlog= Repository.getUsername()
        _image.value =1
        _title.value =""
        _description.value =""
        _publishButton.value=null
        Log.i("usernamess",Repository.getUsername())
    }

    fun setuserlog(author:String){
        _userlog.value=author
    }

    fun titleText(s: CharSequence, start: Int, before: Int, count: Int) {
        _title.value = s.toString()
    }

    fun descriptionText(s: CharSequence, start: Int, before: Int, count: Int) {
        _description.value = s.toString()
    }

    fun checkData(){
        if(_title.value.toString().isNotEmpty() && _description.value.toString().isNotEmpty()){

            this._publishButton.value=true
            //FIXME (QHB) : WARNING, totally wrong. Dont instanciate a ViewModel directly.
            // If you need to share data between ViewModels, use a custom Repository singleton class.
            //FIXME (QHB) :Don't use "magic numbers". What is 1 here ?
            HomeViewModel().addPublication(Publication(1,title.value.toString(),description.value.toString() ))
        }else{
            _publishButton.value=false
        }
    }

    fun setTakePicture(){
        _takePicture.value=false
    }

    fun canTakePicture(){
        _takePicture.value=true
    }

}