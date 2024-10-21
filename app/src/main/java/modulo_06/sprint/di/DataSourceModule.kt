package modulo_06.sprint.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import modulo_06.sprint.retrofit.ApiDataSource
import modulo_06.sprint.room.AppDao
import modulo_06.sprint.room.DbDataSource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    companion object {
        const val BASE_URL = "https://my-json-server.typicode.com/"
        const val ENDPOINT_NAME = "?inc=name"
        const val ENDPOINT_PICTURE = "?inc=picture"
        const val ENDPOINT_LOCATION = "?inc=location"
    }

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient =  OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): ApiDataSource =
        retrofit.create(ApiDataSource::class.java)

    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context: Context): DbDataSource {
        return Room.databaseBuilder(
            context,
            DbDataSource::class.java,
            "user_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun appDao(db: DbDataSource): AppDao = db.dbDao()

}