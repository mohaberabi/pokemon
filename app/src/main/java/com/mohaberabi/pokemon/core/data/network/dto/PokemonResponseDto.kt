package com.mohaberabi.pokemon.core.data.network.dto

import com.google.gson.annotations.SerializedName

data class PokemonResponseDto(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PokemonDto>
)