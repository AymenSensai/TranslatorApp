package com.aymen.translatorapp.feature_translator.data.remote.dto

import com.aymen.translatorapp.feature_translator.domain.models.ResponseData
import com.google.gson.annotations.SerializedName

data class ResponseDataDto(
    @SerializedName("translatedText")
    val translatedText: String,
    @SerializedName("match")
    val match: Double
) {
    fun toResponseData(): ResponseData {
        return ResponseData(translatedText = translatedText, match = match)
    }
}