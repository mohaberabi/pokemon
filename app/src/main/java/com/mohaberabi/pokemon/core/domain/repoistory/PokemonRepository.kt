package com.mohaberabi.pokemon.core.domain.repoistory

import com.mohaberabi.pokemon.core.data.network.util.AppResult
import com.mohaberabi.pokemon.core.domain.model.Pokemon
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {


    suspend fun getPokemons(
        page: Int,
    ): AppResult<List<Pokemon>>

    fun getPokemonInfo(
        pokemon: Pokemon,
    ): Flow<PokemonInfo?>
}