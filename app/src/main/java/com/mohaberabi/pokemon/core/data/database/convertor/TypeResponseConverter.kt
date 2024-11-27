package com.mohaberabi.pokemon.core.data.database.convertor

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.JsonAdapter
import com.google.gson.reflect.TypeToken
import com.mohaberabi.pokemon.core.domain.model.PokemonInfo
import java.sql.Types
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    private val gson: Gson = Gson()

) {


    @TypeConverter
    fun fromPokemonTypeList(types: List<PokemonInfo.PokemonTypeData>): String {
        return gson.toJson(types)
    }

    @TypeConverter
    fun toPokemonTypeList(typesString: String): List<PokemonInfo.PokemonTypeData> {
        val type = object : TypeToken<List<PokemonInfo.PokemonTypeData>>() {}.type
        return gson.fromJson(typesString, type)
    }
}
