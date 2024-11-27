package com.mohaberabi.pokemon.core.domain.source

import com.mohaberabi.pokemon.core.data.network.util.AppResult
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo
import com.mohaberabi.pokemon.core.domain.model.PokemonResponseModel

interface PokemonRemoteDataSource {
    suspend fun getPokemonResponse(
        limit: Int = 20,
        offset: Int = 0,
    ): AppResult<PokemonResponseModel>

    suspend fun getPokemonInfo(
        name: String,
    ): AppResult<PokemonInfo>
}