package g54490.mobg5.sharestudent.model

object Repository{
    private  var username=""

    fun getUsername():String{
        return username
    }

    fun setUsername(name:String){
        this.username=name
    }
}
