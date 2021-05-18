package academy.bangkit.a2992719.core.data.source.remote

import academy.bangkit.a2992719.core.data.source.remote.network.ApiResponse
import academy.bangkit.a2992719.core.data.source.remote.network.ApiService
import academy.bangkit.a2992719.core.data.source.remote.response.MovieResponse
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getTrendingMovies() : Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getTrending()
                val list = response.results
                if(list.isNotEmpty()){
                    emit(ApiResponse.Success(list))
                }else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("WHAT", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}