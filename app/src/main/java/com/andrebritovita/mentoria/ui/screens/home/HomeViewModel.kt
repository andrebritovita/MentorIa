package com.andrebritovita.mentoria.ui.screens.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrebritovita.mentoria.data.repository.StudyPlanRepository
import com.andrebritovita.mentoria.retornoMock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Usa stateflow para manter o estado da tela (studyplan).
class HomeViewModel(
    private val repository: StudyPlanRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())

    // Inicialmente, os valores aqui serão os que foram definidos no HomeUiState
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun loadStudyPlan(topic: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(
                    isLoading = true
                )
                // Indiretamente o ViewModel pede dados para o Respository que decide
                // de onde vem os dados
                //val result = repository.fetchStudyPlan(topic)
                val result = retornoMock
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    studyPlan = result
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Erro desconhecido"
                )
            }
        }
    }
}
