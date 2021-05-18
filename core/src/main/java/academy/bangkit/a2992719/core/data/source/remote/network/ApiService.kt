package academy.bangkit.a2992719.core.data.source.remote.network

import academy.bangkit.a2992719.core.BuildConfig
import academy.bangkit.a2992719.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getTrending(@Query("page") page : Int = 1,
                            @Query("api_key") key : String = BuildConfig.API_KEY,
                            @Query("sort_by") sort : String = "popularity.desc",
                            @Query("include_adult") withAdult : String = "false",
                            @Query("include_video") withVideo : String = "false"
    ) : ListMovieResponse
}