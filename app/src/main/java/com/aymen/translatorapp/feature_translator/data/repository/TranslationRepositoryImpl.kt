package com.aymen.translatorapp.feature_translator.data.repository

import com.aymen.translatorapp.core.helpers.Resource
import com.aymen.translatorapp.feature_translator.data.remote.TranslationApi
import com.aymen.translatorapp.feature_translator.domain.repository.TranslationRepository
import com.aymen.translatorapp.feature_translator.domain.models.Translation

class TranslationRepositoryImpl(private val api: TranslationApi) : TranslationRepository {
    override suspend fun translate(
        query: String,
        from: String,
        to: String
    ): Resource<Translation> {
        try {
            api.translate(
                query = query,
                langPair = "$from|$to"
            ).also {
                return if (it.isSuccessful) {
                    Resource.Success(data = it.body()!!.toTranslation())
                } else {
                    Resource.Error(errorMessage = it.errorBody()?.string().toString())
                }
            }
        } catch (e: Exception) {
            return Resource.Error(errorMessage = e.message.toString())
        }
    }
}