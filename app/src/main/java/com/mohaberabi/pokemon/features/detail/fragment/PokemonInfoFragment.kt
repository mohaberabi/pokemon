package com.mohaberabi.pokemon.features.detail.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mohaberabi.pokemon.R
import com.mohaberabi.pokemon.databinding.FragmentPokemonInfoBinding
import com.mohaberabi.pokemon.features.detail.viewmodel.PokemonInfoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonInfoFragment : Fragment() {

    private val viewmodel by viewModels<PokemonInfoViewModel>()
    private var _binding: FragmentPokemonInfoBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonInfoBinding.inflate(
            layoutInflater,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewmodel = viewmodel
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}