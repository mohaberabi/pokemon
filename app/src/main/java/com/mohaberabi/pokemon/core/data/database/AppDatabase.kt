package com.mohaberabi.pokemon.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mohaberabi.pokemon.core.data.database.convertor.TypeResponseConverter
import com.mohaberabi.pokemon.core.data.database.dao.PokemonDao
import com.mohaberabi.pokemon.core.data.database.dao.PokemonInfoDao
import com.mohaberabi.pokemon.core.data.database.entity.PokemonEntity
import com.mohaberabi.pokemon.core.data.database.entity.PokemonInfoEntity


@Database(
    entities = [PokemonInfoEntity::class, PokemonEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    abstract fun pokemonInfoDao(): PokemonInfoDao

}