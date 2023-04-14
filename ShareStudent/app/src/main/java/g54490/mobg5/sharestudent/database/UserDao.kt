package g54490.mobg5.sharestudent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert fun insert(user: User)
    @Update fun update(user: User)
    @Query("SELECT * from user")
    fun getAllUsers(): List<User>

    @Query("SELECT * from user WHERE emailAdress = :key")
    fun get(key: String): User?

}