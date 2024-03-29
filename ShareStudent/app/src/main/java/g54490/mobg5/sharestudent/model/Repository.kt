package g54490.mobg5.sharestudent.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object Repository{
    private  var username=""
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore by lazy {//une référence statique à une classe liée au contexte Android ne peut etre stocker dans un champ statique
        FirebaseFirestore.getInstance()
    }
    private var storage= FirebaseStorage.getInstance()
    var pub=Publication("","","","","")

    private val _publicationList = MutableLiveData<List<Publication>>()
    val publicationList: LiveData<List<Publication>> = _publicationList

    private val _myPublicationLists = MutableLiveData<List<Publication>>()
    val myPublicationLists: LiveData<List<Publication>> = _myPublicationLists

    private val _onFailureCreatePublication = MutableLiveData<Boolean>()
    val onFailureCreatePublication: LiveData<Boolean> = _onFailureCreatePublication

    private val _onFailureReadData = MutableLiveData<Boolean>()
    val onFailureReadData: LiveData<Boolean> = _onFailureReadData

    private val _onFailureDeleteElementById = MutableLiveData<Boolean>()
    val onFailureDeleteElementById: LiveData<Boolean> = _onFailureDeleteElementById

    fun getUsername():String{
        return username
    }

    fun setUsername(name:String){
        this.username=name
    }

    fun getAuth(): FirebaseAuth {
        return auth
    }

    fun getStorageReference(): StorageReference {
        return storage.reference
    }

    fun getStorage(): FirebaseStorage {
        return storage
    }

    fun createPublication(pub: Publication){
        val publication = hashMapOf(
            "author" to pub.author,
            "description" to pub.description,
            "image" to pub.image,
            "title" to pub.title
        )
        db.collection("publication").add(publication)
            .addOnSuccessListener {}
            .addOnFailureListener { e ->
                _onFailureCreatePublication.value=true
            }
    }

    fun readData() {
        db.collection("publication")
            .get().addOnSuccessListener { result ->
                val publications = mutableListOf<Publication>()
                val myPublications = mutableListOf<Publication>()
                var publicationElt: Publication
                for (document in result) {
                    publicationElt=Publication(
                        document.id,
                        document.data["image"] as String,
                        document.data["title"] as String,
                        document.data["description"] as String,
                        document.data["author"] as String
                    )
                    if (publicationElt.author== username){
                        myPublications.add(publicationElt)
                    }
                    publications.add(publicationElt)
                }
                updatePublicationList(publications)
                updateMyPublicationList(myPublications)
            }
            .addOnFailureListener {
                _onFailureReadData.value=true
            }
    }

    private fun updatePublicationList(newList: List<Publication>) {
        _publicationList.value = newList
    }

    private fun updateMyPublicationList(newList: List<Publication>) {
        _myPublicationLists.value = newList
    }

    fun deleteElementById(id: String){
        db.collection("publication")
            .document(id).delete()
            .addOnSuccessListener {}
            .addOnFailureListener {
                _onFailureDeleteElementById.value=true
            }
    }

    fun getPublicationWithId(id:String){
        publicationList.value.let {
            if (it != null) {
                for (publication in it) {
                    if (publication.id==id){
                        pub= publication
                    }
                }
            }
        }

    }
}



