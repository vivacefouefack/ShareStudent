package g54490.mobg5.sharestudent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import g54490.mobg5.sharestudent.model.Publication
import g54490.mobg5.sharestudent.model.Repository

class DetailForProfilViewModel : ViewModel(){

    val publication: Publication
        get()= Repository.pub

    private val _canDelete = MutableLiveData<Boolean>()
    val canDelete: LiveData<Boolean>
        get() = _canDelete

    private var onFailureDeleteElementById:Boolean = false

    init {
        Repository.onFailureDeleteElementById.observeForever {
            if (it==true){
                onFailureDeleteElementById=true
            }
        }
    }

    fun setCanDelete() {
        _canDelete.value = true
    }

    fun getOnFailureDelete():Boolean {
        return onFailureDeleteElementById
    }

    fun setOnFailureDelete(){
        onFailureDeleteElementById=false
    }

    fun deletePublication(){
        Repository.deleteElementById(publication.id)
    }

    fun updatePublicationList(){
        Repository.readData()
    }
}