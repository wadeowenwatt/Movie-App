package com.example.movieapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding : LayoutBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private val argument : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutBottomSheetBinding.inflate(inflater)
        binding.collapsingLayout.minimumHeight = 350
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // setting movieId for each film
        viewModel.movieId = argument.id
        viewModel.run()

        // Setting back btn
        binding.backBtn.setOnClickListener {
            it.findNavController().popBackStack()
        }

        // binding view
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when {
                    uiState.isLoading -> {
                        binding.nestScrollView.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    uiState.detail.isNotEmpty() -> {
                        binding.nestScrollView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        val iconUrl = "https://image.tmdb.org/t/p/w500${uiState.detail[0].banner}"
                        val uri = iconUrl.toUri().buildUpon().scheme("https").build()
                        binding.banner.load(uri)

                        val title = uiState.detail[0].title!!.split(":")
                        if (title.size == 1)
                            binding.filmName.text = uiState.detail[0].title
                        else {
                            binding.filmName.text = title[0]
                            binding.filmEps.text = title[1]
                        }

                        binding.movieGenre.text = uiState.detail[0].genres[0].name

                        binding.ageAdmit.text = if (uiState.detail[0].adult) "18+" else "16+"

                        binding.imdbScore.text = getString(R.string.imdb_detail_fragment, uiState.detail[0].imdb?.toDouble())

                        binding.description.text = uiState.detail[0].description

                        val adapterCast = CastAdapter(uiState.detail[0].cast!!)
                        binding.listCast.adapter = adapterCast

                    }
                    uiState.error.isNotEmpty() -> {}
                }
            }
        }
    }
}