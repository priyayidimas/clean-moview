package academy.bangkit.a2992719.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse (
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results : List<MovieResponse>,

    @field:SerializedName("total_pages")
    val totalPages : Int
)