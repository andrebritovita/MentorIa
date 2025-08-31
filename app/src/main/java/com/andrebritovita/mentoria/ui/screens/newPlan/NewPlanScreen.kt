package com.andrebritovita.mentoria.ui.screens.newPlan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewPlanScreen(
    viewModel: NewPlanViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var topic by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = topic,
            onValueChange = { topic = it },
            label = { Text("Digite o tema de estudo") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { viewModel.generateStudyPlan(topic) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Gerar Plano de Estudos")
        }

        when (uiState) {
            is NewPlanUiState.Idle -> Text("Digite um tema e gere seu plano.")
            is NewPlanUiState.Loading -> CircularProgressIndicator()
            is NewPlanUiState.Success -> Text((uiState as NewPlanUiState.Success).message)
            is NewPlanUiState.Error -> Text("Erro: ${(uiState as NewPlanUiState.Error).error}")
        }
    }
}