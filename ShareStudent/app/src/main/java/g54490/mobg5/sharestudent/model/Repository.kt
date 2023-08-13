package g54490.mobg5.sharestudent.model

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object Repository{
    private  var username=""
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    @SuppressLint("StaticFieldLeak")
    private val db = Firebase.firestore
    private var storage= FirebaseStorage.getInstance()
    private var publicationLists:MutableList<Publication> = mutableListOf()
    private var myPublicationList:MutableList<Publication> = mutableListOf()

    var pub=Publication("","","","","")

    fun getUsername():String{
        return username
    }

    fun setUsername(name:String){
        this.username=name
    }

    fun getAuth(): FirebaseAuth {
        return auth
    }

    //FIXME (QHB) :why do you return a MutableList and not a List?
    fun getAllPublications(): MutableList<Publication> {
        return publicationLists
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
        val publicationLists = mutableListOf<Publication>() // Créez la liste ici

        db.collection("publication")
            .get().addOnSuccessListener { result ->
                for (document in result) {
                    publicationLists.add(Publication(
                        document.id,
                        document.data["image"] as String,
                        document.data["title"] as String,
                        document.data["description"] as String,
                        document.data["author"] as String
                    ))
                }
                Log.d("repo", "success")
                callback.onSuccess(publicationLists) // Appel onSuccess avec la liste complète
                Log.d("repo", Repository.publicationLists.size.toString())
            }
            .addOnFailureListener { exception ->
                Log.d("error", "Error getting documents.", exception)
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



