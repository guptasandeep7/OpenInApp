package com.sandeepgupta.openinapp.domain.di


import com.sandeepgupta.openinapp.BuildConfig
import com.sandeepgupta.openinapp.data.datasource.ApiService
import com.sandeepgupta.openinapp.domain.repository.Repository
import com.sandeepgupta.openinapp.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(OkHttpClient().newBuilder().addInterceptor { chain ->
            val request =
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN}")
                    .build()
            chain.proceed(request)
        }.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesRepository(
        apiService: ApiService,
    ): Repository =
        RepositoryImpl(apiService)

}