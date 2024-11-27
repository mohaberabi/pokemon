package com.mohaberabi.pokemon.core.data.network.dto

import com.google.gson.annotations.SerializedName

data class PokemonDto(
    var page: Int = 0,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                "pokemon/other/official-artwork/$index.png"
    }

    fun formattedName(): String = name.replaceFirstChar { it.uppercase() }
}