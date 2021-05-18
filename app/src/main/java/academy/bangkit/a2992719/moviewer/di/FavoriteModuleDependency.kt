package academy.bangkit.a2992719.moviewer.di

import academy.bangkit.a2992719.core.domain.usecase.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependency {

    fun provideMovieUseCase() : MovieUseCase

}