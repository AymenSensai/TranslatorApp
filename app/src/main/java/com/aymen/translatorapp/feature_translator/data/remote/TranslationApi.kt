package com.aymen.translatorapp.feature_translator.data.remote

import com.aymen.translatorapp.feature_translator.data.remote.dto.TranslationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TranslationApi {
    @GET("get")
    suspend fun translate(
        @Query("q") query:String,
        @Query("langpair") langPair:String
    ): Response<TranslationDto>
}