package com.andrebritovita.mentoria.data.repository

import android.util.Log
import com.andrebritovita.mentoria.data.model.ChatRequest
import com.andrebritovita.mentoria.data.model.Message
import com.andrebritovita.mentoria.data.remote.RetrofitClient

class StudyPlanRepository {
}


suspend fun fetchStudyPlan(topic: String): String {
    val request = ChatRequest(
        messages = listOf(
            Message("system", "Você é um mentor de estudos."),
            Message(
                "user",
                "Monte um plano de estudos rápido sobre o tema: $topic. " +
                        "- Liste de 3 a 6 tópicos principais em ordem de aprendizado. " +
                        "- Sugira 1 desafio prático para cada tópico. " +
                        "- No final, crie 3 perguntas de quiz curtas para testar o conhecimento."
            )
        )
    )

    val response = RetrofitClient.api.getStudyPlan(request)
    Log.d("MarketRepository.kt", "retorno:  $response")
    return response.choices.firstOrNull()?.message?.content ?: "Nenhuma resposta encontrada."
}
