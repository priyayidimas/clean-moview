package academy.bangkit.a2992719.favorite

import academy.bangkit.a2992719.core.domain.usecase.MovieUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class FavoriteActivityViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()
}