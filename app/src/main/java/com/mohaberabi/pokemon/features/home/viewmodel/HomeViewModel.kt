package com.mohaberabi.pokemon.features.home.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.pokemon.core.data.network.util.onFailure
import com.mohaberabi.pokemon.core.data.network.util.onSuccess
import com.mohaberabi.pokemon.core.domain.model.Pokemon
import com.mohaberabi.pokemon.core.domain.repoistory.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class HomeState(
    val pokemon: List<Pokemon> = listOf(),
    val loading: Boolean = false,
    val error: String = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {

        const val PAGE_INDEX = "PAGE_INDEX"
    }


    private val pokemonPageFlow = savedStateHandle.getStateFlow(PAGE_INDEX, 0)
    private val _state = MutableStateFlow(HomeState())

    val state = _state.onStart { getPokemons() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            HomeState()
        )


    private fun getPokemons(page: Int = 0) {
        viewModelScope.launch {
            pokemonRepository.getPokemons(page)
                .onSuccess { poks ->
                    _state.update { it.copy(pokemon = it.pokemon + poks) }
                }.onFailure { }
        }
    }

    fun getMorePokemons() {
        val newPage = pokemonPageFlow.value + 1
        savedStateHandle[PAGE_INDEX] = newPage
        getPokemons(page = newPage)
    }
}