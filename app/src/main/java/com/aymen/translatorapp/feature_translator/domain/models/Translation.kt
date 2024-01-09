package com.aymen.translatorapp.feature_translator.domain.models

data class Translation(
    val responseData: ResponseData,
    val quotaFinished: Boolean,
    val responseDetails: String,
    val responseStatus: Int,
    val responderId: Int?,
    val exceptionCode: Int,
    val matches: List<Match>
)
