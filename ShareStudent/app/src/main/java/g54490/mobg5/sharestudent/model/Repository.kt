package g54490.mobg5.sharestudent.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

object Repository{
    private  var username=""
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private var storage= FirebaseStorage.getInstance().reference
    private var publicationList:List<Publication>?=null

    fun getUsername():String{
        return username
    }

    fun setUsername(name:String){
        this.username=name
    }

    fun getAuth(): FirebaseAuth {
        return auth
    }

    fun getAllPublication(): List<Publication>? {
        return publicationList
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
                publicationList=result.toObjects(Publication::class.java)
                for (document in result) {
                    Log.i("data", "${document.id} => ${document.data}")
                    Log.i("database","un exemple *******************************")
                }
            }
            .addOnFailureListener { exception ->
                //Log.w("data", "Error getting documents.", exception)
                Log.i("database","un exemple #####################################")
            }
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


