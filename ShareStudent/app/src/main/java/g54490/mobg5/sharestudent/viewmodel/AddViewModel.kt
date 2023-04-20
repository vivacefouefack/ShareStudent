package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication

class AddViewModel:ViewModel() {
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

    init {
        _image.value=1
        _title.value =""
        _description.value =""
        _publishButton.value=null
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
            HomeViewModel().addPublication(Publication(1,title.value.toString(),description.value.toString()))
        }else{
            _publishButton.value=false
        }
    }

}