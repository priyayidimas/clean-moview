package academy.bangkit.a2992719.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val id: Long,
    val title: String,
    val overview: String?,
    val popularity: Double?,
    val voteAverage: Double?,
    val posterUrl: String?,
    val releaseDate: String?,
    val isFavorite: Boolean
) : Parcelable