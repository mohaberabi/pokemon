package com.mohaberabi.pokemon.core.data.database.mapper

import com.mohaberabi.pokemon.core.data.database.entity.PokemonEntity
import com.mohaberabi.pokemon.core.data.database.entity.PokemonInfoEntity
import com.mohaberabi.pokemon.core.data.network.dto.PokemonDto
import com.mohaberabi.pokemon.core.data.network.dto.PokemonInfoDto
import com.mohaberabi.pokemon.core.data.network.dto.PokemonResponseDto
import com.mohaberabi.pokemon.core.domain.model.Pokemon
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo
import com.mohaberabi.pokemon.core.domain.model.PokemonResponseModel


fun Pokemon.toPokemonEntity() =
    PokemonEntity(
        page = page,
        name = name,
        url = url,
    )


fun PokemonInfo.toPokemonInfoEntity() = PokemonInfoEntity(
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
    hp = hp,
    imageUlr = imageUrl
)

fun PokemonEntity.toPokemon() =
    Pokemon(
        page = page,
        name = name,
        url = url,
        imageUrl = getImageUrl(),
        formattedName = formattedName()
    )


fun PokemonInfoEntity.toPokemonInfo() = PokemonInfo(
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
    hp = hp,
    imageUrl = imageUlr
)