package g54490.mobg5.sharestudent.model

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import io.grpc.Context.Storage

object Repository{
    private  var username=""
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private var storage= FirebaseStorage.getInstance()
    private var publicationLists:MutableList<Publication> = mutableListOf()
    private var myPublicationList:MutableList<Publication> = mutableListOf()

    var isWifiConn: Boolean = false
    var isMobileConn: Boolean = false
    var canRead: Boolean = true

    var pub=Publication("","","","","")

    fun getUsername():String{
        return username
    }

    fun setUsername(name:String){
        this.username=name
        getmyPublication()
    }

    fun getAuth(): FirebaseAuth {
        return auth
    }

    fun getAllPublications(): MutableList<Publication> {
        return publicationLists
    }

    fun getStorageReference(): StorageReference {
        return storage.reference
    }

    fun getStorage(): FirebaseStorage {
        return storage
    }

    init {
        //readData()
    }

    fun getMyPublications(): MutableList<Publication> {
        return myPublicationList
    }

    fun getmyPublication(){
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

    fun readData(){
        if (publicationLists.isNotEmpty()){
            publicationLists.removeAll(publicationLists)
        }
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
            }
            .addOnFailureListener { exception ->
                Log.w("error", "Error getting documents.", exception)
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
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}



