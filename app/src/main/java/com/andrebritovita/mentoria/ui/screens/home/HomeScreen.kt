package com.andrebritovita.mentoria.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    // O .collectAsState converte de StateFlow para State
    // Inicialmente, começará com os valores padrão.
    // O state observa o stateFlow. Toda vez que houver uma resposta da API
    // o estado muda e a UI mostra o novo valor.

    LaunchedEffect(Unit) {
        viewModel.loadStudyPlan("Kotlin")
    }

    when {
        uiState.isLoading -> {
            Text(uiState.studyPlan, modifier = Modifier.padding(16.dp))
        }
        uiState.error != null -> {
            Text("Erro: ${uiState.error}", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
        }
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(uiState.studyPlan)
            }
        }
    }
}