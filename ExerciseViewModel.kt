// File: app/src/main/java/com/example/dumbbellswap/ui/viewmodel/ExerciseViewModel.kt

package com.example.dumbbellswap.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dumbbellswap.database.AppDatabase
import com.example.dumbbellswap.database.Exercise
import com.example.dumbbellswap.database.InventoryItem
import com.example.dumbbellswap.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExerciseRepository
    val allExercises: LiveData<List<Exercise>>
    val allInventory: LiveData<List<InventoryItem>>
    val maxWeight: LiveData<Int>

    private val _searchResults = MutableLiveData<List<Exercise>>()
    val searchResults: LiveData<List<Exercise>> = _searchResults

    private val _filteredResults = MutableLiveData<List<Exercise>>()
    val filteredResults: LiveData<List<Exercise>> = _filteredResults

    private val _currentQuery = MutableLiveData<String>()
    val currentQuery: LiveData<String> = _currentQuery

    init {
        val database = AppDatabase.getDatabase(application)
        repository = ExerciseRepository(database)
        allExercises = repository.allExercises
        allInventory = repository.allInventory
        maxWeight = repository.maxWeight

        viewModelScope.launch {
            repository.initializeSampleData()
        }
    }

    fun searchExercises(query: String) {
        _currentQuery.value = query
        val trimmedQuery = query.trim()

        allExercises.observeForever { exercises ->
            if (trimmedQuery.isNotEmpty()) {
                val results = exercises.filter { it.name.contains(trimmedQuery, ignoreCase = true) }
                _searchResults.value = results
                filterResultsByInventory(results)
            } else {
                _searchResults.value = emptyList()
                _filteredResults.value = emptyList()
            }
        }
    }

    fun filterResultsByInventory(results: List<Exercise>) {
        maxWeight.observeForever { max ->
            if (max != null) {
                val filtered = results.filter { it.requiredWeight <= max }
                _filteredResults.value = filtered
            } else {
                _filteredResults.value = emptyList()
            }
        }
    }

    fun updateInventoryItem(item: InventoryItem) {
        viewModelScope.launch {
            repository.updateInventoryItem(item)
        }
    }
}