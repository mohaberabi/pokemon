package com.mohaberabi.pokemon.core.data.soure

import com.mohaberabi.pokemon.core.data.network.mapper.toPokemonInfo
import com.mohaberabi.pokemon.core.data.network.mapper.toPokemonResponse
import com.mohaberabi.pokemon.core.data.network.service.PokemonServices
import com.mohaberabi.pokemon.core.data.network.util.AppResult
import com.mohaberabi.pokemon.core.data.network.util.map
import com.mohaberabi.pokemon.core.data.network.util.safeApiCall
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo
import com.mohaberabi.pokemon.core.domain.model.PokemonResponseModel
import com.mohaberabi.pokemon.core.domain.source.PokemonRemoteDataSource
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class RetrofitPokemonRemoteDataSource @Inject constructor(
    private val api: PokemonServices
) : PokemonRemoteDataSource {
    override suspend fun getPokemonResponse(
        limit: Int,
        offset: Int
    ): AppResult<PokemonResponseModel> {

        return api.getPokemonResponse(limit, offset).safeApiCall().map { it.toPokemonResponse() }

    }

    override suspend fun getPokemonInfo(
        name: String,
    ): AppResult<PokemonInfo> {
        return api.getPokemonInfo(name).safeApiCall().map { it.toPokemonInfo() }

    }
}