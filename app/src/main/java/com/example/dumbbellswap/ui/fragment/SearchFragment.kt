// File: app/src/main/java/com/example/dumbbellswap/ui/fragment/SearchFragment.kt

package com.example.dumbbellswap.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dumbbellswap.R
import com.example.dumbbellswap.ui.adapter.ExerciseAutoCompleteAdapter
import com.example.dumbbellswap.ui.viewmodel.ExerciseViewModel
import com.google.android.material.button.MaterialButton

class SearchFragment : Fragment() {

    private val viewModel: ExerciseViewModel by viewModels()
    private lateinit var searchInput: AutoCompleteTextView
    private lateinit var findButton: MaterialButton
    private lateinit var suggestionsList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchInput = view.findViewById(R.id.searchInput)
        findButton = view.findViewById(R.id.findButton)
        suggestionsList = view.findViewById(R.id.suggestionsList)

        // Set up auto-complete
        viewModel.allExercises.observe(viewLifecycleOwner) { exercises ->
            val exerciseNames = exercises.map { it.name }.distinct()
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                exerciseNames
            )
            searchInput.setAdapter(adapter)
        }

        findButton.setOnClickListener {
            val query = searchInput.text.toString()
            if (query.isNotEmpty()) {
                viewModel.searchExercises(query)

                // Navigate to results with the query
                val bundle = Bundle().apply {
                    putString("query", query)
                }
                findNavController().navigate(R.id.action_searchFragment_to_resultsFragment, bundle)
            }
        }
    }
}
