package com.mohaberabi.pokemon.core.data.network.service

import com.mohaberabi.pokemon.core.data.network.dto.PokemonInfoDto
import com.mohaberabi.pokemon.core.data.network.dto.PokemonResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonServices {


    @GET("pokemon")
    suspend fun getPokemonResponse(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): Response<PokemonResponseDto>


    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String,
    ): Response<PokemonInfoDto>
}