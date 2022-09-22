package com.irv205.xpacex.core.di

import com.irv205.xpacex.data.repository.SpaceXRepository
import com.irv205.xpacex.data.repository.SpaceXRepositoryImplement
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(spaceXRepositoryImplement: SpaceXRepositoryImplement): SpaceXRepository = spaceXRepositoryImplement
}