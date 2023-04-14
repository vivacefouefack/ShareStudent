package g54490.mobg5.sharestudent.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false)
    var emailAdress: String,

    @ColumnInfo(name = "registrationTime")
    val registrationTime: Long,

    @ColumnInfo(name = "registrationDate")
    var registrationDate: Long

)
