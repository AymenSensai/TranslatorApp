package com.aymen.translatorapp.core.di

import com.aymen.translatorapp.core.helpers.Constants.BASE_URL
import com.aymen.translatorapp.feature_translator.data.remote.TranslationApi
import com.aymen.translatorapp.feature_translator.data.repository.TranslationRepositoryImpl
import com.aymen.translatorapp.feature_translator.domain.repository.TranslationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTranslationApi(): TranslationApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTelegramRepository(
        api: TranslationApi
    ): TranslationRepository {
        return TranslationRepositoryImpl(api)
    }

}