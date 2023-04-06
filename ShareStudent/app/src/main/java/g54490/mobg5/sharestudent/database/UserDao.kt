package g54490.mobg5.sharestudent.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend  fun update(user: User)

    @Query("SELECT * from user")
    suspend fun getAllUser()

}