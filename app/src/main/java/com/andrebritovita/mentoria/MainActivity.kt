package com.andrebritovita.mentoria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.andrebritovita.mentoria.data.local.AppDatabase
import com.andrebritovita.mentoria.data.repository.StudyPlanRepository
import com.andrebritovita.mentoria.ui.screens.home.HomeScreen
import com.andrebritovita.mentoria.ui.screens.home.HomeViewModel
import com.andrebritovita.mentoria.ui.screens.home.HomeViewModelFactory
import com.andrebritovita.mentoria.ui.theme.MentorIATheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<HomeViewModel> {
        val dao = AppDatabase.getDatabase(this)
        val getStudyPlanDao = dao.studyPlanDao()
        val repository = StudyPlanRepository(getStudyPlanDao)
        // Cria o HomeViewModel usando o HomeViewModelFactory
        HomeViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MentorIATheme {
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}

/*@Composable
fun StudyPlanScreen(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("Carregando...") }

    LaunchedEffect(Unit) {

        text = fetchStudyPlan("Kotlin")
    }

    Text(
        text = text,
        modifier = modifier
    )
}*/

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MentorIATheme {
        Text(
            text = "text",
        )
    }
}

val retornoMock =
    "ChatResponse(choices=[Choice(message=Message(role=assistant, content=### Plano de Estudos Rápido sobre Kotlin\n" +
            "                                                                                                    \n" +
            "                                                                                                    #### Tópicos Principais:\n" +
            "                                                                                                    \n" +
            "                                                                                                    1. **Introdução ao Kotlin**\n" +
            "                                                                                                       - **Desafio Prático:** Crie um programa simples que exiba \"Olá, Mundo!\" no console.\n" +
            "                                                                                                    \n" +
            "                                                                                                    2. **Sintaxe Básica e Tipos de Dados**\n" +
            "                                                                                                       - **Desafio Prático:** Escreva um programa que declare variáveis de diferentes tipos (Int, String, Boolean) e imprima seus valores no console.\n" +
            "                                                                                                    \n" +
            "                                                                                                    3. **Estruturas de Controle**\n" +
            "                                                                                                       - **Desafio Prático:** Desenvolva um programa que peça ao usuário um número e informe se ele é par ou ímpar, utilizando condicionais.\n" +
            "                                                                                                    \n" +
            "                                                                                                    4. **Funções e Escopos**\n" +
            "                                                                                                       - **Desafio Prático:** Crie uma função que receba dois números inteiros como parâmetros e retorne a soma deles. Imprima o resultado no console.\n" +
            "                                                                                                    \n" +
            "                                                                                                    5. **Programação Orientada a Objetos (POO)**\n" +
            "                                                                                                       - **Desafio Prático:** Defina uma classe `Carro` com propriedades como `marca`, `modelo`, e `ano`. Crie um objeto dessa classe e imprima suas propriedades.\n" +
            "                                                                                                    \n" +
            "                                                                                                    6. **Manipulação de Coleções**\n" +
            "                                                                                                       - **Desafio Prático:** Crie uma lista de números inteiros, filtre os números pares e imprima essa nova lista no console.\n" +
            "                                                                                                    \n" +
            "                                                                                                    ### Quiz de Conhecimento\n" +
            "                                                                                                    \n" +
            "                                                                                                    1. O que é Kotlin e onde ele é comumente usado?\n" +
            "                                                                                                       \n" +
            "                                                                                                    2. Qual é a diferença entre `val` e `var` em Kotlin?\n" +
            "                                                                                                    \n" +
            "                                                                                                    3. Como você define uma função em Kotlin? Dê um exemplo simples.))])"