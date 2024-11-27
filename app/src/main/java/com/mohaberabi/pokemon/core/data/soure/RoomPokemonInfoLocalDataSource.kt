package com.mohaberabi.pokemon.core.data.soure

import com.mohaberabi.pokemon.core.data.database.dao.PokemonInfoDao
import com.mohaberabi.pokemon.core.data.database.mapper.toPokemonInfo
import com.mohaberabi.pokemon.core.data.database.mapper.toPokemonInfoEntity
import com.mohaberabi.pokemon.core.domain.model.DispatchersProvider
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo
import com.mohaberabi.pokemon.core.domain.source.PokemonDetailLocalDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomPokemonInfoLocalDataSource @Inject constructor(
    private val dispatchers: DispatchersProvider,
    private val dao: PokemonInfoDao
) : PokemonDetailLocalDataSource {


    override suspend fun insertPokemonInfo(info: PokemonInfo) {

        withContext(dispatchers.io) {
            dao.insertPokemonInfo(info.toPokemonInfoEntity())
        }
    }

    override suspend fun getPokemonInfo(name: String): PokemonInfo? {
        return withContext(dispatchers.io) {
            dao.getPokemonInfo(name)?.toPokemonInfo()
        }
    }
}