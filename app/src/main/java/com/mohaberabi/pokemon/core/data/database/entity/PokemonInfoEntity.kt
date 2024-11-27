package com.mohaberabi.pokemon.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo

@Entity
data class PokemonInfoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<PokemonInfo.PokemonTypeData>,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val exp: Int,
    val imageUlr: String,
)
