// File: app/src/main/java/com/example/dumbbellswap/api/ExerciseApiService.kt

package com.example.dumbbellswap.api

import com.example.dumbbellswap.database.Exercise
import retrofit2.http.GET
import retrofit2.http.Path

interface ExerciseApiService {
    @GET("exercises/name/{name}")
    suspend fun getExercisesByName(@Path("name") name: String): List<ExerciseResponse>

    @GET("exercises/equipment/{equipment}")
    suspend fun getExercisesByEquipment(@Path("equipment") equipment: String): List<ExerciseResponse>
}

data class ExerciseResponse(
    val id: String,
    val name: String,
    val target: String,
    val equipment: String,
    val gifUrl: String
) {
    fun toExercise(substituteName: String, substituteTarget: String): Exercise {
        return Exercise(
            name = name,
            targetMuscle = target,
            requiredWeight = 0,
            substituteName = substituteName,
            substituteTarget = substituteTarget,
            gifUrl = gifUrl
        )
    }
}