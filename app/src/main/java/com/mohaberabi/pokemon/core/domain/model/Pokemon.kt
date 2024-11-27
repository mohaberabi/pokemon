package com.mohaberabi.pokemon.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    var page: Int = 0,
    val name: String,
    val url: String,
    val imageUrl: String,
    val formattedName: String,
) : Parcelable