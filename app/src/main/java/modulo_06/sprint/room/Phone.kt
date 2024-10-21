package modulo_06.sprint.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Phone")
data class Phone(
    @PrimaryKey(autoGenerate = true) var my_id: Int = 0,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "lastPrice") val lastPrice: Int,
    @ColumnInfo(name = "credit") val credit: Boolean

)
