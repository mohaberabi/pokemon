package com.mohaberabi.pokemon.features.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mohaberabi.pokemon.R
import com.mohaberabi.pokemon.databinding.FragmentHomeBinding
import com.mohaberabi.pokemon.features.home.PokemonsListAdapter
import com.mohaberabi.pokemon.features.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var adpater: PokemonsListAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        adpater = PokemonsListAdapter(
            onClick = {
                findNavController().navigate(HomeFragmentDirections.goInfo(it))
            },
        )
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.adapter = adpater

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}