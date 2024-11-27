package com.mohaberabi.pokemon.core.domain.source

import com.mohaberabi.pokemon.core.domain.model.PokemonInfo

interface PokemonDetailLocalDataSource {
    suspend fun insertPokemonInfo(info: PokemonInfo)
    suspend fun getPokemonInfo(name: String): PokemonInfo?
}