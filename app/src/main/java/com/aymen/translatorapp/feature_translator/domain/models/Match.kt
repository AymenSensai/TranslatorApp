package com.aymen.translatorapp.feature_translator.domain.models

data class Match(
    val id: Long,
    val segment: String,
    val translation: String,
    val source: String,
    val target: String,
    val quality: Int,
    val reference: String?,
    val usageCount: Int,
    val subject: String,
    val createdBy: String,
    val lastUpdatedBy: String,
    val createdDate: String,
    val lastUpdatedDate: String,
    val match: Double
)
