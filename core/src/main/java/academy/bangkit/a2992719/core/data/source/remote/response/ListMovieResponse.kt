package academy.bangkit.a2992719.core.data.source.remote.response

data class ListMovieResponse (
    val page: Int,
    val results : List<MovieResponse>,
    val total_pages : Int
)