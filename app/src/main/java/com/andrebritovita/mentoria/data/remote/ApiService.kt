package com.andrebritovita.mentoria.data.remote

import com.andrebritovita.mentoria.BuildConfig
import com.andrebritovita.mentoria.data.model.ChatRequest
import com.andrebritovita.mentoria.data.model.ChatResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApi {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer ${BuildConfig.API_KEY}" // coloque sua API Key aqui ou via Interceptor
    )

    @POST("chat/completions")
    suspend fun getStudyPlan(@Body request: ChatRequest): ChatResponse
}
