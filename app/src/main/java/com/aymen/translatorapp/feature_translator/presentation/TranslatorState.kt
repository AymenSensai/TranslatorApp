package com.aymen.translatorapp.feature_translator.presentation

import com.aymen.translatorapp.feature_translator.domain.models.Translation

data class TranslatorState(
    val translation: Translation? = null,
    val source: Lang = Lang.English,
    val target: Lang = Lang.Arabic,
    val query: String = "",
    val isLoading: Boolean = false
)
