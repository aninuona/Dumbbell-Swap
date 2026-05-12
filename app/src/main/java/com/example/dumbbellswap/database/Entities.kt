// File: app/src/main/java/com/example/dumbbellswap/database/Entities.kt

package com.example.dumbbellswap.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val targetMuscle: String,
    val requiredWeight: Int,
    val substituteName: String,
    val substituteTarget: String
)

@Entity(tableName = "inventory")
data class InventoryItem(
    @PrimaryKey
    val weightLbs: Int,
    val isOwned: Boolean = false
)
