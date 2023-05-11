package g54490.mobg5.sharestudent.model

import androidx.lifecycle.LiveData

data class Publication(
    var image: Int,
    var title: String,
    var description: String,
    var author: String
){
    constructor(): this(1, "","","")
}
