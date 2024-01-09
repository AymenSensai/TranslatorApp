package com.aymen.translatorapp.feature_translator.presentation

sealed class TranslatorEvent {

    data class Translate(val query: String) : TranslatorEvent()
    data object Clear : TranslatorEvent()
    data class OnSourceChange(val lang: Lang) : TranslatorEvent()
    data class OnTargetChange(val lang: Lang) : TranslatorEvent()

}