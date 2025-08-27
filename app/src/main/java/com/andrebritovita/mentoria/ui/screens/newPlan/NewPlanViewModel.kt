package com.andrebritovita.mentoria.ui.screens.newPlan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrebritovita.mentoria.data.repository.StudyPlanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewPlanViewModel(
    private val repository: StudyPlanRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<NewPlanUiState>(NewPlanUiState.Idle)
    val uiState: StateFlow<NewPlanUiState> = _uiState.asStateFlow()

    fun generateStudyPlan(topic: String) {
        viewModelScope.launch {
            _uiState.value = NewPlanUiState.Loading
            try {
                val result = repository.fetchStudyPlan(topic)
                _uiState.value = NewPlanUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = NewPlanUiState.Error(e.message ?: "Erro inesperado")
            }
        }
    }
}