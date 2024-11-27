package com.mohaberabi.pokemon.core.data.soure

import com.mohaberabi.pokemon.core.data.database.dao.PokemonDao
import com.mohaberabi.pokemon.core.data.database.mapper.toPokemon
import com.mohaberabi.pokemon.core.data.database.mapper.toPokemonEntity
import com.mohaberabi.pokemon.core.domain.model.DispatchersProvider
import com.mohaberabi.pokemon.core.domain.model.Pokemon
import com.mohaberabi.pokemon.core.domain.source.PokemonLocalDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRoomLocalDataSource @Inject constructor(
    private val dao: PokemonDao,
    private val dispatchers: DispatchersProvider
) : PokemonLocalDataSource {


    override suspend fun getAllPokemon(page: Int): List<Pokemon> {
        return withContext(dispatchers.io) {
            dao.getAllPokemon(page).map { it.toPokemon() }
        }
    }

    override suspend fun getPokemonList(page: Int): List<Pokemon> {
        return withContext(dispatchers.io) {
            dao.getPokemonList(page).map { it.toPokemon() }
        }
    }

    override suspend fun insertPokemon(pokemon: List<Pokemon>) {
        return withContext(dispatchers.io) {
            dao.insertPokemon(pokemon.map { it.toPokemonEntity() })
        }
    }
}