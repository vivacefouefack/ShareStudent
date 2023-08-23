package g54490.mobg5.sharestudent.model

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
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
    private var publicationLists:MutableList<Publication> = mutableListOf()
    private var myPublicationList:MutableList<Publication> = mutableListOf()
    var pub=Publication("","","","","")

    private val _publicationList = MutableLiveData<MutableList<Publication>?>()
    val publicationList: MutableLiveData<MutableList<Publication>?>
        get() = _publicationList

    init {
        _publicationList.value=null
    }

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

    fun getMyPublications(): MutableList<Publication> {
        if (myPublicationList.isNotEmpty()){
            myPublicationList.removeAll(myPublicationList)
        }
        getmyPublication()
        return myPublicationList
    }

   private fun getmyPublication(){
        for (publication in publicationLists) {
            if (publication.author== username){
                myPublicationList.add(publication)
            }
        }
   }

    fun createPublication(pub: Publication){
        val publication = hashMapOf(
            "author" to pub.author,
            "description" to pub.description,
            "image" to pub.image,
            "title" to pub.title
        )
        db.collection("publication").add(publication)
            .addOnSuccessListener { documentReference ->
                Log.d("database", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("database", "Error adding document", e)
            }
    }

    interface FirebaseSuccessListener {
        fun onSuccess(publications: MutableList<Publication>)
        fun onError()
    }

    fun readData(callback: FirebaseSuccessListener) {
        db.collection("publication")
            .get().addOnSuccessListener { result ->
                publicationLists.removeAll(publicationLists)
                for (document in result) {
                    publicationLists.add(Publication(
                        document.id,
                        document.data["image"] as String,
                        document.data["title"] as String,
                        document.data["description"] as String,
                        document.data["author"] as String
                    ))

                    /*publicationList.value?.add(
                        Publication(
                            document.id,
                            document.data["image"] as String,
                            document.data["title"] as String,
                            document.data["description"] as String,
                            document.data["author"] as String
                        )
                    )*/
                }
                Log.i("check","readData")
                Log.i("check", _publicationList.value?.size.toString())
                callback.onSuccess(publicationLists) // Appel onSuccess avec la liste complète
            }
            .addOnFailureListener { exception ->
                callback.onError() // Appel onError en cas d'erreur
            }
    }

    fun getPublicationWithId(id:String){
        for (publication in publicationLists) {
            if (publication.id==id){
                pub= publication
            }
        }
    }

    fun isOnline(connMgr: ConnectivityManager): Boolean {
        //FIXME (QHB) : don't user deprecated methods
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}



