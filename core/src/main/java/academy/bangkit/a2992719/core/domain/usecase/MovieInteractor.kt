package academy.bangkit.a2992719.core.domain.usecase

import academy.bangkit.a2992719.core.domain.model.Movie
import academy.bangkit.a2992719.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val repository: IMovieRepository) : MovieUseCase {

    override fun getTrendingMovies() = repository.getTrendingMovies()

    override fun getFavoriteMovies() = repository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie,
                                  state: Boolean) = repository.setFavoriteMovie(movie, state)

}