// File: app/src/main/java/com/example/dumbbellswap/ui/fragment/ResultsFragment.kt

package com.example.dumbbellswap.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dumbbellswap.R
import com.example.dumbbellswap.ui.adapter.ResultsAdapter
import com.example.dumbbellswap.ui.viewmodel.ExerciseViewModel

class ResultsFragment : Fragment() {

    private val viewModel: ExerciseViewModel by viewModels()
    private lateinit var resultsList: RecyclerView
    private lateinit var emptyStateText: TextView
    private lateinit var queryTitle: TextView
    private val adapter = ResultsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultsList = view.findViewById(R.id.resultsList)
        emptyStateText = view.findViewById(R.id.emptyStateText)
        queryTitle = view.findViewById(R.id.queryTitle)

        resultsList.layoutManager = LinearLayoutManager(requireContext())
        resultsList.adapter = adapter

        val query = arguments?.getString("query") ?: ""
        queryTitle.text = "Searching for: $query"

        viewModel.currentQuery.observe(viewLifecycleOwner) { currentQuery ->
            if (currentQuery == query) {
                viewModel.filteredResults.observe(viewLifecycleOwner) { results ->
                    if (results.isEmpty()) {
                        resultsList.visibility = View.GONE
                        emptyStateText.visibility = View.VISIBLE
                        emptyStateText.text = "No swaps found. Try adjusting your inventory."
                    } else {
                        resultsList.visibility = View.VISIBLE
                        emptyStateText.visibility = View.GONE
                        adapter.submitList(results)
                    }
                }
            }
        }

        viewModel.searchExercises(query)
    }
}
