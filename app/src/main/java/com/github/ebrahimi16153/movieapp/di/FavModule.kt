package com.github.ebrahimi16153.movieapp.di

import android.content.Context
import androidx.room.Room
import com.github.ebrahimi16153.movieapp.data.FavDataBase
import com.github.ebrahimi16153.movieapp.data.FavMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object FavModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FavDataBase::class.java, "favDataBase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()



    @Singleton
    @Provides
    fun provideFavDao(dataBase: FavDataBase) = dataBase.movieDao



    @Singleton
    @Provides
    fun provideFavMovie() = FavMovie()


}