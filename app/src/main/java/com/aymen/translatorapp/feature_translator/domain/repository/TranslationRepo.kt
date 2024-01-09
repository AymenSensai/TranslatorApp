package com.aymen.translatorapp.feature_translator.domain.repository

import com.aymen.translatorapp.core.helpers.Resource
import com.aymen.translatorapp.feature_translator.domain.models.Translation

interface TranslationRepository {
    suspend fun translate(query:String,from:String,to:String): Resource<Translation>
}