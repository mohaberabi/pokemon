package com.mohaberabi.pokemon.core.data.network.mapper

import com.mohaberabi.pokemon.core.data.network.dto.PokemonDto
import com.mohaberabi.pokemon.core.data.network.dto.PokemonInfoDto
import com.mohaberabi.pokemon.core.data.network.dto.PokemonResponseDto
import com.mohaberabi.pokemon.core.domain.model.Pokemon
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo
import com.mohaberabi.pokemon.core.domain.model.PokemonResponseModel


fun PokemonDto.toPokemon() =
    Pokemon(
        page = page,
        name = name,
        url = url,
        imageUrl = getImageUrl(),
        formattedName = formattedName()
    )

fun PokemonResponseDto.toPokemonResponse() =
    PokemonResponseModel(count, next, previous, results.map { it.toPokemon() })

fun PokemonInfoDto.toPokemonInfo() = PokemonInfo(
    id = id,
    name = name,
    speed = speed,
    types = types.map { res ->
        PokemonInfo.PokemonTypeData(
            res.slot,
            PokemonInfo.PokemonData(res.type.name)
        )
    },
    defense = defense,
    exp = exp,
    experience = experience,
    height = height,
    weight = weight,
    attack = attack,
    hp = hp
)