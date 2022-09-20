package com.irv205.xpacex.di

import com.irv205.xpacex.BuildConfig
import com.irv205.xpacex.data.ApiSpacesX
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
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): ApiProvider = ApiProvider(BuildConfig.API_BASE_URL)

    @Singleton
    @Provides
    fun providesUserApi(apiProvider: ApiProvider): ApiSpacesX = apiProvider.getEndpoint(ApiSpacesX::class.java)
}