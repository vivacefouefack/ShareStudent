package g54490.mobg5.sharestudent.model

import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object Repository{
    private  var username=""
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private var storage= FirebaseStorage.getInstance().reference
    private var publicationLists:MutableList<Publication> = mutableListOf()

    var isWifiConn: Boolean = false
    var isMobileConn: Boolean = false

    fun getUsername():String{
        return username
    }

    fun setUsername(name:String){
        this.username=name
    }

    fun getAuth(): FirebaseAuth {
        return auth
    }

    fun getAllPublications(): MutableList<Publication> {
        return publicationLists
    }

    fun getStorage(): StorageReference {
        return storage
    }

    init {
        readData()
        //publication.value=Publication("","","",""",""","")
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

    fun getPublicationWithId(id:String): Publication {
        var pub=Publication("","","","","")
        for (publication in publicationLists) {
            if (publication.id==id){
                pub= publication
            }
        }
        return pub
    }

    fun isConnect(connMgr: ConnectivityManager):Boolean{
        connMgr.allNetworks.forEach { network ->
            connMgr.getNetworkInfo(network)?.apply {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    isWifiConn = isWifiConn or isConnected
                }
                if (type == ConnectivityManager.TYPE_MOBILE) {
                    isMobileConn = isMobileConn or isConnected
                }
            }
        }
        return isWifiConn || isMobileConn
    }

}
//get and show image
/*val image="usb"
val storage=FirebaseStorage.getInstance().reference.child("images/usb.jpg")

val localFile= File.createTempFile("tempImage","jpg")
storage.getFile(localFile).addOnSuccessListener {
    val bitmap=BitmapFactory.decodeFile(localFile.absolutePath)
    binding.imageView5.setImageBitmap(bitmap)
}.addOnFailureListener{
    Log.i("imageOnFire","error to get image from firebase")
}*/


