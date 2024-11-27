package com.mohaberabi.pokemon.core.presentation.utils

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    private val predicate: (T) -> String,
    private val contentPredicate: (T, T) -> Boolean,
) :
    ListAdapter<T, VH>(
        AppDiffCallBack<T>(
            predicate = predicate,
            contentPredicate = contentPredicate
        )
    )