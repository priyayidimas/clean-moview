package academy.bangkit.a2992719.moviewer.home

import academy.bangkit.a2992719.core.domain.usecase.MovieUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getTrendingMovies().asLiveData()
}