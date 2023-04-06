package g54490.mobg5.sharestudent.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false)
    var emailAdress: String,

    @ColumnInfo(name = "registrationTime")
    val registrationTime: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    var registrationDate: LocalDate =LocalDate.now()

)
