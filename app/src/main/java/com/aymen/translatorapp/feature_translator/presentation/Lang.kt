package com.aymen.translatorapp.feature_translator.presentation

import androidx.annotation.DrawableRes
import com.aymen.translatorapp.R

sealed class Lang(
    @DrawableRes val icon: Int,
    val name: String,
    val symbol: String
) {
    data object English : Lang(name = "English", icon = R.drawable.english, symbol = "en")
    data object Arabic : Lang(name = "Arabic", icon = R.drawable.arabic, symbol = "ar")
    data object Spanish : Lang(name = "Spanish", icon = R.drawable.spanish, symbol = "es")
    data object Japanese : Lang(name = "Japanese", icon = R.drawable.japanese, symbol = "ja")
}