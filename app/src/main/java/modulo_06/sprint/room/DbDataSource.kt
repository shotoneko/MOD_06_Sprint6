package modulo_06.sprint.room

import androidx.room.Database
import androidx.room.RoomDatabase
import modulo_06.sprint.retrofit.ApiPhone

@Database(
    entities = [Phone::class],
    version = 2
)
abstract class DbDataSource: RoomDatabase() {
    abstract fun dbDao(): AppDao
}