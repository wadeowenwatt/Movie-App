package com.example.movieapp.presentation.navBottom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieapp.databinding.FragmentShuffleBinding

class ShuffleFragment : Fragment() {

    private var _binding : FragmentShuffleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShuffleBinding.inflate(inflater)
        return binding.root
    }
}