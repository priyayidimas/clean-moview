package academy.bangkit.a2992719.moviewer.detail

import academy.bangkit.a2992719.core.domain.model.Movie
import academy.bangkit.a2992719.core.domain.usecase.MovieUseCase
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailActivityViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}