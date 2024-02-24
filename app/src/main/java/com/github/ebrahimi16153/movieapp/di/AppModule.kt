package com.github.ebrahimi16153.movieapp.di

import com.github.ebrahimi16153.movieapp.api.ApiServices
import com.github.ebrahimi16153.movieapp.utils.Constants.BASE_URL
import com.github.ebrahimi16153.movieapp.utils.Constants.CONNECTION_TIME_OUT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideConnectionTimeOut() = CONNECTION_TIME_OUT


    @Singleton
    @Provides
    fun provideGson() = GsonBuilder().setLenient().create()


    @Singleton
    @Provides
    fun provideInterceptor() = HttpLoggingInterceptor().apply {

        level = HttpLoggingInterceptor.Level.BODY
    }


    @Singleton
    @Provides
    fun provideClient(time: Long, interceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .writeTimeout(time, TimeUnit.SECONDS)
            .readTimeout(time, TimeUnit.SECONDS)
            .connectTimeout(time, TimeUnit.SECONDS)
            .build()


    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson, baseUrl: String): ApiServices = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiServices::class.java)

}