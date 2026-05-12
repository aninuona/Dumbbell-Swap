// File: app/src/main/java/com/example/dumbbellswap/repository/ExerciseRepository.kt

package com.example.dumbbellswap.repository

import androidx.lifecycle.LiveData
import com.example.dumbbellswap.database.AppDatabase
import com.example.dumbbellswap.database.Exercise
import com.example.dumbbellswap.database.InventoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository(private val db: AppDatabase) {

    val allExercises: LiveData<List<Exercise>> = db.exerciseDao().getAllExercises()
    val allInventory: LiveData<List<InventoryItem>> = db.inventoryDao().getAllInventory()
    val maxWeight: LiveData<Int> = db.inventoryDao().getMaxWeight()

    fun searchExercises(query: String): LiveData<List<Exercise>> {
        return db.exerciseDao().searchExercises(query)
    }

    suspend fun initializeSampleData() = withContext(Dispatchers.IO) {
        // Pre-populate with sample exercises
        val sampleExercises = listOf(
            Exercise(1, "Lat Pulldown", "Lats", 10, "Dumbbell Pullover", "Lats"),
            Exercise(2, "Lat Pulldown", "Lats", 10, "Renegade Row", "Lats"),
            Exercise(3, "Barbell Bench Press", "Chest", 15, "Dumbbell Bench Press", "Chest"),
            Exercise(4, "Leg Extension", "Quads", 20, "Dumbbell Goblet Squat", "Quads"),
            Exercise(5, "Leg Curl", "Hamstrings", 15, "Single Leg Deadlift", "Hamstrings"),
            Exercise(6, "Shoulder Press", "Shoulders", 10, "Dumbbell Shoulder Press", "Shoulders"),
            Exercise(7, "Barbell Squat", "Legs", 20, "Dumbbell Goblet Squat", "Legs"),
            Exercise(8, "Deadlift", "Back", 25, "Dumbbell Deadlift", "Back"),
            Exercise(9, "Bicep Curl", "Biceps", 5, "Dumbbell Curl", "Biceps"),
            Exercise(10, "Tricep Dip", "Triceps", 0, "Dumbbell Tricep Extension", "Triceps")
        )

        val sampleInventory = listOf(
            InventoryItem(5, false),
            InventoryItem(10, false),
            InventoryItem(15, false),
            InventoryItem(20, false),
            InventoryItem(25, false),
            InventoryItem(30, false)
        )

        db.exerciseDao().insertExercises(sampleExercises)
        db.inventoryDao().insertInventoryItems(sampleInventory)
    }

    suspend fun updateInventoryItem(item: InventoryItem) = withContext(Dispatchers.IO) {
        db.inventoryDao().updateInventoryItem(item)
    }
}
