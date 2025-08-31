package com.andrebritovita.mentoria.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.andrebritovita.mentoria.retornoMock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = false,
    val studyPlan: String = "Carregando...",
    val error: String? = null
)