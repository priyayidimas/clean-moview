package academy.bangkit.a2992719.core.data

import academy.bangkit.a2992719.core.data.source.local.LocalDataSource
import academy.bangkit.a2992719.core.data.source.remote.RemoteDataSource
import academy.bangkit.a2992719.core.data.source.remote.network.ApiResponse
import academy.bangkit.a2992719.core.data.source.remote.response.MovieResponse
import academy.bangkit.a2992719.core.domain.model.Movie
import academy.bangkit.a2992719.core.domain.repository.IMovieRepository
import academy.bangkit.a2992719.core.utils.AppExecutors
import academy.bangkit.a2992719.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getTrendingMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getTrendingMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val list = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(list)
            }

        }.asFlow()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val entity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(entity, state) }
    }
}