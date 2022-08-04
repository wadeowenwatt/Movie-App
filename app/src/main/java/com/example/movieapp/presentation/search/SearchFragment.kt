package com.example.movieapp.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private val argument: SearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set query
        viewModel.query = argument.query
        viewModel.run()

        binding.backBtn.setOnClickListener {
            it.findNavController().popBackStack()
        }

        // binding view
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when {
                    uiState.isLoading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.listSearchRecyclerView.visibility = View.GONE
                        binding.searchScreenNotFound.visibility = View.GONE
                    }
                    uiState.search.isNotEmpty() -> {
                        binding.progressBar.visibility = View.GONE
                        val adapter = SearchAdapter(uiState.search)
                        binding.listSearchRecyclerView.visibility = View.VISIBLE
                        binding.searchScreenNotFound.visibility = View.GONE
                        binding.listSearchRecyclerView.adapter = adapter
                    }
                    uiState.search.isEmpty() -> {
                        binding.progressBar.visibility = View.GONE
                        binding.listSearchRecyclerView.visibility = View.GONE
                        binding.searchScreenNotFound.visibility = View.VISIBLE
                    }
                    uiState.error.isNotEmpty() -> {}
                }
            }
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query == "") {
                    viewModel.query = "a"
                    viewModel.run()
                } else {
                    viewModel.query = query
                    viewModel.run()
                }
                return true
            }
        })
    }

}