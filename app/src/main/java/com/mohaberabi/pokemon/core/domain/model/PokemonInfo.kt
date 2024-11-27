package com.mohaberabi.pokemon.core.domain.model

data class PokemonInfo(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<PokemonTypeData>,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val exp: Int,
    val imageUrl: String = "",
) {
    data class PokemonTypeData(
        val slot: Int,
        val type: PokemonData
    )

    data class PokemonData(
        val name: String
    )

    companion object {
        const val MAX_HP = 300
        const val MAX_ATTACK = 300
        const val MAX_DEFENSE = 300
        const val MAX_SPEED = 300
        const val MAX_EXP = 1000
    }

}

