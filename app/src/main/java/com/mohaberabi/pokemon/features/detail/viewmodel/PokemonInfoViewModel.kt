package com.mohaberabi.pokemon.features.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.pokemon.core.domain.model.Pokemon
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo
import com.mohaberabi.pokemon.core.domain.repoistory.PokemonRepository
import com.mohaberabi.pokemon.features.home.viewmodel.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class PokemonInfoState(
    val loading: Boolean = false,
    val info: PokemonInfo? = null,
)

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    companion object {
        const val POKEMON = "pokemon"
    }


    private val _state = MutableStateFlow(PokemonInfoState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.getStateFlow<Pokemon?>(POKEMON, null)
            .flatMapLatest { poke ->
                poke?.let {
                    repository.getPokemonInfo(it)
                } ?: flowOf()
            }.onStart { _state.update { it.copy(loading = true) } }
            .onEach { info -> _state.update { it.copy(loading = false, info = info) } }
            .onCompletion { _state.update { it.copy(loading = false) } }
            .launchIn(viewModelScope)


    }


}