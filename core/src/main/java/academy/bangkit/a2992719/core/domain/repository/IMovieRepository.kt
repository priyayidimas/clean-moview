package academy.bangkit.a2992719.core.domain.repository

import academy.bangkit.a2992719.core.data.Resource
import academy.bangkit.a2992719.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getTrendingMovies() : Flow<Resource<List<Movie>>>

    fun getFavoriteMovies() : Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

}