package com.mohaberabi.pokemon.core.data.di

import com.mohaberabi.pokemon.core.data.database.dao.PokemonDao
import com.mohaberabi.pokemon.core.data.database.dao.PokemonInfoDao
import com.mohaberabi.pokemon.core.data.network.service.PokemonServices
import com.mohaberabi.pokemon.core.data.repository.OfflineFirstPokemonRepository
import com.mohaberabi.pokemon.core.data.soure.PokemonRoomLocalDataSource
import com.mohaberabi.pokemon.core.data.soure.RetrofitPokemonRemoteDataSource
import com.mohaberabi.pokemon.core.data.soure.RoomPokemonInfoLocalDataSource
import com.mohaberabi.pokemon.core.domain.model.DefaultDispatchersProvider
import com.mohaberabi.pokemon.core.domain.model.DispatchersProvider
import com.mohaberabi.pokemon.core.domain.repoistory.PokemonRepository
import com.mohaberabi.pokemon.core.domain.source.PokemonDetailLocalDataSource
import com.mohaberabi.pokemon.core.domain.source.PokemonLocalDataSource
import com.mohaberabi.pokemon.core.domain.source.PokemonRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object DataModule {


    @Singleton
    @Provides
    fun provideDispatchersProvider(): DispatchersProvider = DefaultDispatchersProvider()

    @Singleton
    @Provides
    fun provideInfoLocalDataSource(
        dispatchers: DispatchersProvider,
        dao: PokemonInfoDao
    ): PokemonDetailLocalDataSource = RoomPokemonInfoLocalDataSource(
        dispatchers = dispatchers,
        dao = dao,
    )

    @Singleton
    @Provides
    fun providePokemonDao(
        dispatchers: DispatchersProvider,
        dao: PokemonDao
    ): PokemonLocalDataSource = PokemonRoomLocalDataSource(
        dispatchers = dispatchers,
        dao = dao,
    )

    @Singleton
    @Provides
    fun provideRemoteSource(
        api: PokemonServices
    ): PokemonRemoteDataSource = RetrofitPokemonRemoteDataSource(api)


    @Singleton
    @Provides
    fun providePokemonRepository(
        detailLocal: PokemonDetailLocalDataSource,
        pokemonLocal: PokemonLocalDataSource,
        pokemonRemoteDataSource: PokemonRemoteDataSource,
        dispatchers: DispatchersProvider,
    ): PokemonRepository = OfflineFirstPokemonRepository(
        dispatchers = dispatchers,
        pokemonLocalDataSource = pokemonLocal,
        pokemonRemoteDataSource = pokemonRemoteDataSource,
        pokemonDetailLocalDataSource = detailLocal
    )
}