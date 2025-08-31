package com.andrebritovita.mentoria.data.repository

import android.util.Log
import com.andrebritovita.mentoria.data.local.dao.StudyPlanDao
import com.andrebritovita.mentoria.data.local.entities.StudyPlanEntity
import com.andrebritovita.mentoria.data.model.ChatRequest
import com.andrebritovita.mentoria.data.model.Message
import com.andrebritovita.mentoria.data.remote.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class StudyPlanRepository(
    private val studyPlanDao: StudyPlanDao
) {

    suspend fun fetchStudyPlan(topic: String): String {
        return try {
            // Monta a requisição para a API
            val request = ChatRequest(
                messages = listOf(
                    Message(
                        "system",
                        "Você é um mentor de estudos."
                    ),
                    Message(
                        "user",
                        "Monte um plano de estudos rápido sobre o tema: $topic. " +
                                "- Liste de 3 a 6 tópicos principais em ordem de aprendizado. " +
                                "- Sugira 1 desafio prático para cada tópico. " +
                                "- No final, crie 3 perguntas de quiz curtas para testar o conhecimento."
                    )
                )
            )

            // Chama a API
            val response = RetrofitClient.api.getStudyPlan(request)

            // Extrai a primeira resposta (choice) e exibe o conteúdo (message.content)
            val content = response.choices.firstOrNull()?.message?.content
                ?: "Nenhuma resposta encontrada."

            // Cria uma Entity com a resposta da API
            val entity = StudyPlanEntity(topic = topic, content = content)
            // Salva a resposta no banco local
            studyPlanDao.insertStudyPlan(entity)

            Log.d("StudyPlanRepository", "Plano de estudos salvo com sucesso: $topic")
            // Retorna o conteúdo para ser usado no ViewModel
            return content
        } catch (e: Exception) {
            Log.e("StudyPlanRepository", "Erro ao buscar plano de estudos", e)

            // Tenta pegar alguma resposta salva no banco local
            val cached = studyPlanDao.getStudyPlanByTopic(topic)
            cached?.content ?: "Erro ao buscar o plano de estudos e nenhum cache disponível."
        }
    }
}
