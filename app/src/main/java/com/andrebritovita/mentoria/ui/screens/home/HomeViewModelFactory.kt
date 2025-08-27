package com.andrebritovita.mentoria.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andrebritovita.mentoria.data.repository.StudyPlanRepository

// Usa o padrão de projetos Factory para criar um ViewModel
// Cria um viewmodel injetando no construtor um objeto StudyPlanRepository
class HomeViewModelFactory(private val repository: StudyPlanRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}