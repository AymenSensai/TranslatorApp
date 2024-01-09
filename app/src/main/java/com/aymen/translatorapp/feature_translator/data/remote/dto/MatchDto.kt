package com.aymen.translatorapp.feature_translator.data.remote.dto

import com.aymen.translatorapp.feature_translator.domain.models.Match
import com.google.gson.annotations.SerializedName

data class MatchDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("segment")
    val segment: String,
    @SerializedName("translation")
    val translation: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("target")
    val target: String,
    @SerializedName("quality")
    val quality: Int,
    @SerializedName("reference")
    val reference: String?,
    @SerializedName("usage-count")
    val usageCount: Int,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("created-by")
    val createdBy: String,
    @SerializedName("last-updated-by")
    val lastUpdatedBy: String,
    @SerializedName("create-date")
    val createdDate: String,
    @SerializedName("last-update-date")
    val lastUpdatedDate: String,
    @SerializedName("match")
    val match: Double
) {
    fun toMatch(): Match {
        return Match(
            id = id,
            segment = segment,
            translation = translation,
            source = source,
            target = target,
            quality = quality,
            reference = reference,
            usageCount = usageCount,
            subject = subject,
            createdBy = createdBy,
            lastUpdatedBy = lastUpdatedBy,
            createdDate = createdDate,
            lastUpdatedDate = lastUpdatedDate,
            match = match
        )
    }
}
