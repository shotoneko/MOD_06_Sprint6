package modulo_06.sprint.room


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(phone: Phone)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(phones: List<Phone>)

    @Delete
    fun delete(phone: Phone)

    @Query("SELECT * FROM Phone ORDER BY id DESC")
    fun getAll(): Flow<List<Phone>>

}