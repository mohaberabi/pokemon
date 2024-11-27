package com.mohaberabi.pokemon.core.data.network.dto

import com.google.gson.annotations.SerializedName
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo.Companion.MAX_ATTACK
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo.Companion.MAX_DEFENSE
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo.Companion.MAX_EXP
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo.Companion.MAX_HP
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo.Companion.MAX_SPEED
import kotlin.random.Random

data class PokemonInfoDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("base_experience") val experience: Int,
    @SerializedName("types") val types: List<TypeResponseDto>,
    val hp: Int = Random.nextInt(MAX_HP),
    val attack: Int = Random.nextInt(MAX_ATTACK),
    val defense: Int = Random.nextInt(MAX_DEFENSE),
    val speed: Int = Random.nextInt(MAX_SPEED),
    val exp: Int = Random.nextInt(MAX_EXP),
) {
    data class TypeResponseDto(
        @SerializedName("slot") val slot: Int,
        @SerializedName("type") val type: TypeDto
    )

    data class TypeDto(
        @SerializedName("name") val name: String
    )


}