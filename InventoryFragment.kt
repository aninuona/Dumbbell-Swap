// File: app/src/main/java/com/example/dumbbellswap/ui/fragment/InventoryFragment.kt

package com.example.dumbbellswap.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dumbbellswap.R
import com.example.dumbbellswap.ui.adapter.InventoryAdapter
import com.example.dumbbellswap.ui.viewmodel.ExerciseViewModel

class InventoryFragment : Fragment() {

    private val viewModel: ExerciseViewModel by viewModels()
    private lateinit var inventoryList: RecyclerView
    private lateinit var adapter: InventoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inventoryList = view.findViewById(R.id.inventoryList)
        adapter = InventoryAdapter { item ->
            viewModel.updateInventoryItem(item)
        }

        inventoryList.layoutManager = LinearLayoutManager(requireContext())
        inventoryList.adapter = adapter

        viewModel.allInventory.observe(viewLifecycleOwner) { inventory ->
            adapter.submitList(inventory)
        }
    }
}