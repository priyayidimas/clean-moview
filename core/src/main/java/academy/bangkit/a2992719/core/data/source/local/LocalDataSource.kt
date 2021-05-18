package academy.bangkit.a2992719.core.data.source.local

import academy.bangkit.a2992719.core.data.source.local.entity.MovieEntity
import academy.bangkit.a2992719.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovies() : Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavoriteMovies() : Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun setFavoriteMovie(movie : MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovies(movie)
    }

}