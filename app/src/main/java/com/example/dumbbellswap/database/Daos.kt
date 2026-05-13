// File: app/src/main/java/com/example/dumbbellswap/database/Daos.kt

package com.example.dumbbellswap.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercises(exercises: List<Exercise>)

    @Query("SELECT * FROM exercises WHERE name LIKE '%' || :query || '%'")
    fun searchExercises(query: String): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): LiveData<List<Exercise>>
}

@Dao
interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInventoryItems(items: List<InventoryItem>)

    @Update
    fun updateInventoryItem(item: InventoryItem)

    @Query("SELECT * FROM inventory ORDER BY weightLbs ASC")
    fun getAllInventory(): LiveData<List<InventoryItem>>

    @Query("SELECT MAX(weightLbs) FROM inventory WHERE isOwned = 1")
    fun getMaxWeight(): LiveData<Int>

    @Query("DELETE FROM inventory")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM exercises")
    fun getCount(): Int
}
