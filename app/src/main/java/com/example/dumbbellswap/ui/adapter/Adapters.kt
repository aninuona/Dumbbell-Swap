// File: app/src/main/java/com/example/dumbbellswap/ui/adapter/Adapters.kt

package com.example.dumbbellswap.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dumbbellswap.R
import com.example.dumbbellswap.database.Exercise
import com.example.dumbbellswap.database.InventoryItem

// Results Adapter
class ResultsAdapter : ListAdapter<Exercise, ResultsAdapter.ResultsViewHolder>(ExerciseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result, parent, false)
        return ResultsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        val exercise = getItem(position)
        holder.bind(exercise)
    }

    class ResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val substituteName: TextView = itemView.findViewById(R.id.substituteName)
        private val targetMuscle: TextView = itemView.findViewById(R.id.targetMuscle)
        private val requiredWeight: TextView = itemView.findViewById(R.id.requiredWeight)

        fun bind(exercise: Exercise) {
            substituteName.text = exercise.substituteName
            targetMuscle.text = "Target: ${exercise.substituteTarget}"
            requiredWeight.text = "Required: ${exercise.requiredWeight} lbs"
        }
    }

    class ExerciseDiffUtil : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise) =
            oldItem == newItem
    }
}

// Inventory Adapter
class InventoryAdapter(
    private val onItemToggle: (InventoryItem) -> Unit
) : ListAdapter<InventoryItem, InventoryAdapter.InventoryViewHolder>(InventoryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inventory, parent, false)
        return InventoryViewHolder(view, onItemToggle)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class InventoryViewHolder(
        itemView: View,
        private val onItemToggle: (InventoryItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val weightLabel: TextView = itemView.findViewById(R.id.weightLabel)
        private val toggleSwitch: Switch = itemView.findViewById(R.id.toggleSwitch)

        fun bind(item: InventoryItem) {
            weightLabel.text = "${item.weightLbs} lbs"
            toggleSwitch.isChecked = item.isOwned

            toggleSwitch.setOnCheckedChangeListener { _, isChecked ->
                onItemToggle(item.copy(isOwned = isChecked))
            }
        }
    }

    class InventoryDiffUtil : DiffUtil.ItemCallback<InventoryItem>() {
        override fun areItemsTheSame(oldItem: InventoryItem, newItem: InventoryItem) =
            oldItem.weightLbs == newItem.weightLbs

        override fun areContentsTheSame(oldItem: InventoryItem, newItem: InventoryItem) =
            oldItem == newItem
    }
}

// Auto-complete adapter (placeholder)
class ExerciseAutoCompleteAdapter
