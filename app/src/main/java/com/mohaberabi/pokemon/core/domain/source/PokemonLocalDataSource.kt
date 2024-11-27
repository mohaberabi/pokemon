package com.mohaberabi.pokemon.core.domain.source

import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.pokemon.core.data.database.entity.PokemonEntity
import com.mohaberabi.pokemon.core.data.database.entity.PokemonInfoEntity
import com.mohaberabi.pokemon.core.domain.model.Pokemon
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo

interface PokemonLocalDataSource {


    suspend fun insertPokemon(pokemon: List<Pokemon>)

    suspend fun getPokemonList(page: Int): List<Pokemon>

    suspend fun getAllPokemon(page: Int): List<Pokemon>
}