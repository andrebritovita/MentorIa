package com.andrebritovita.mentoria.data.remote
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(60, TimeUnit.SECONDS)   // tempo para conectar
        .readTimeout(60, TimeUnit.SECONDS)      // tempo para receber resposta
        .writeTimeout(60, TimeUnit.SECONDS)     // tempo para enviar requisição
        .build()

    val api: OpenAiApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openai.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAiApi::class.java)
    }
}
