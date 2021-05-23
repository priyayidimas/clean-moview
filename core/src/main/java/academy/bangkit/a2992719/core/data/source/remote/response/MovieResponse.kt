package academy.bangkit.a2992719.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("title", alternate = ["name"])
    val title: String,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("release_date", alternate = ["first_air_date"])
    val releaseDate: String? = null,
) {
    val posterUrl : String?
        get() {
            return if(posterPath != null){
                "https://image.tmdb.org/t/p/w200$posterPath"
            }else{
                null
            }
        }
}