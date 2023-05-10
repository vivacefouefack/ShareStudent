package g54490.mobg5.sharestudent.model

import com.google.firebase.auth.FirebaseAuth

object Repository{
    private  var username=""
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun getUsername():String{
        return username
    }

    fun setUsername(name:String){
        this.username=name
    }

    fun getAuth(): FirebaseAuth {
        return auth
    }
}
