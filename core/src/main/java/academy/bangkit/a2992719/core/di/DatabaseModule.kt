package academy.bangkit.a2992719.core.di

import academy.bangkit.a2992719.core.BuildConfig
import academy.bangkit.a2992719.core.data.source.local.room.MovieDao
import academy.bangkit.a2992719.core.data.source.local.room.MovieDatabase
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        val passPhrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.API_KEY.toCharArray())
        val supportFactory = SupportFactory(passPhrase)
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movie.db")
                   .fallbackToDestructiveMigration()
                   .openHelperFactory(supportFactory)
                   .build()
    }

    @Provides
    fun provideFavoriteDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()
}