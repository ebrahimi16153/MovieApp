package com.github.ebrahimi16153.movieapp.di

import com.github.ebrahimi16153.movieapp.models.register.BodyRegister
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RegisterModule {


    @Provides
    @Singleton
    fun provideBody() = BodyRegister()

}