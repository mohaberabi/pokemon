package com.mohaberabi.pokemon.core.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mohaberabi.pokemon.core.data.network.service.PokemonServices
import com.mohaberabi.pokemon.core.data.network.util.AppResult
import com.mohaberabi.pokemon.core.data.network.util.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object CoreNetworkModule {
//    val gson: Gson = GsonBuilder()
//        .registerTypeAdapter(AppResult::class.java, ApiResponseAdapter<Any>())
//        .create()

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()).build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonServices(retro: Retrofit): PokemonServices =
        retro.create(PokemonServices::class.java)
}