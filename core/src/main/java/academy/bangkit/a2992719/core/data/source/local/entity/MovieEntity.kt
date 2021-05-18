package academy.bangkit.a2992719.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    val id: Long,
    val title: String,
    val overview: String?,
    val popularity: Double?,
    val voteAverage: Double?,
    val posterUrl: String?,
    val releaseDate: String?,
    var isFavorite: Boolean
)