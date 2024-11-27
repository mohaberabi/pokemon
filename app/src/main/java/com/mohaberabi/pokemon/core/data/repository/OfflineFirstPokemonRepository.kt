package com.mohaberabi.pokemon.core.data.repository

import com.mohaberabi.pokemon.core.data.network.util.AppResult
import com.mohaberabi.pokemon.core.data.network.util.map
import com.mohaberabi.pokemon.core.data.network.util.onSuccess
import com.mohaberabi.pokemon.core.domain.model.DispatchersProvider
import com.mohaberabi.pokemon.core.domain.model.Pokemon
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo
import com.mohaberabi.pokemon.core.domain.repoistory.PokemonRepository
import com.mohaberabi.pokemon.core.domain.source.PokemonDetailLocalDataSource
import com.mohaberabi.pokemon.core.domain.source.PokemonLocalDataSource
import com.mohaberabi.pokemon.core.domain.source.PokemonRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class OfflineFirstPokemonRepository @Inject constructor(
    private val pokemonLocalDataSource: PokemonLocalDataSource,
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonDetailLocalDataSource: PokemonDetailLocalDataSource,
    private val dispatchers: DispatchersProvider,
) : PokemonRepository {

    override suspend fun getPokemons(page: Int): AppResult<List<Pokemon>> {
        val localPokes =
            pokemonLocalDataSource.getAllPokemon(page)
        return if (localPokes.isEmpty()) {
            val remoteResponse = pokemonRemoteDataSource.getPokemonResponse(offset = page)
            remoteResponse.map {
                val pokemons = it.results.map { pok -> pok.copy(page = page) }
                pokemonLocalDataSource.insertPokemon(pokemons)
                pokemons
            }

        } else {
            AppResult.Success(localPokes)
        }
    }


    override fun getPokemonInfo(pokemon: Pokemon): Flow<PokemonInfo?> {
        return flow {
            val local =
                pokemonDetailLocalDataSource.getPokemonInfo(pokemon.name)
            if (local != null) {
                emit(local.copy(imageUrl = pokemon.imageUrl))
            } else {
                val remoteResponse = pokemonRemoteDataSource.getPokemonInfo(name = pokemon.name)
                remoteResponse.onSuccess {
                    pokemonDetailLocalDataSource.insertPokemonInfo(it.copy(imageUrl = pokemon.imageUrl))
                    emit(it.copy(imageUrl = pokemon.imageUrl))
                }
            }
        }.flowOn(dispatchers.io)
    }
}