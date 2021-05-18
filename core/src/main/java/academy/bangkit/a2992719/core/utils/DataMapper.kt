package academy.bangkit.a2992719.core.utils

import academy.bangkit.a2992719.core.data.source.local.entity.MovieEntity
import academy.bangkit.a2992719.core.data.source.remote.response.MovieResponse
import academy.bangkit.a2992719.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity>  =
        input.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                popularity = it.popularity,
                posterUrl = it.posterUrl,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                isFavorite = false
            )
        }


    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                popularity = it.popularity,
                posterUrl = it.posterUrl,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        overview = input.overview,
        popularity = input.popularity,
        posterUrl = input.posterUrl,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        isFavorite = input.isFavorite
    )
}