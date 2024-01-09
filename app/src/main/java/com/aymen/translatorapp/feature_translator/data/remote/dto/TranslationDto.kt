package com.aymen.translatorapp.feature_translator.data.remote.dto

import com.aymen.translatorapp.feature_translator.domain.models.Translation
import com.google.gson.annotations.SerializedName

data class TranslationDto(
    @SerializedName("responseData")
    val responseData: ResponseDataDto,
    @SerializedName("quotaFinished")
    val quotaFinished: Boolean,
    @SerializedName("responseDetails")
    val responseDetails: String,
    @SerializedName("responseStatus")
    val responseStatus: Int,
    @SerializedName("responderId")
    val responderId: Int?,
    @SerializedName("exception_code")
    val exceptionCode: Int,
    @SerializedName("matches")
    val matches: List<MatchDto>
) {
    fun toTranslation(): Translation {
        return Translation(
            responseData = responseData.toResponseData(),
            quotaFinished = quotaFinished,
            responseDetails = responseDetails,
            responseStatus = responseStatus,
            responderId = responderId,
            exceptionCode = exceptionCode,
            matches = matches.map { it.toMatch() }
        )
    }
}