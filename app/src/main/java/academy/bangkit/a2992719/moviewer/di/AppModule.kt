package academy.bangkit.a2992719.moviewer.di

import academy.bangkit.a2992719.core.domain.usecase.MovieInteractor
import academy.bangkit.a2992719.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

}