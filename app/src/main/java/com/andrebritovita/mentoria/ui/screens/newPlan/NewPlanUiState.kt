package com.andrebritovita.mentoria.ui.screens.newPlan

sealed interface NewPlanUiState {
    object Idle : NewPlanUiState
    object Loading : NewPlanUiState
    data class Success(val message: String) : NewPlanUiState
    data class Error(val error: String) : NewPlanUiState
}