package com.mohaberabi.pokemon.core.presentation.utils

import androidx.recyclerview.widget.DiffUtil

class AppDiffCallBack<T : Any>(
    private val predicate: (T) -> String,
    private val contentPredicate: (T, T) -> Boolean,
) : DiffUtil.ItemCallback<T>() {
    override fun areContentsTheSame(
        oldItem: T,
        newItem: T,
    ): Boolean = contentPredicate(oldItem, newItem)

    override fun areItemsTheSame(
        oldItem: T,
        newItem: T,
    ): Boolean {
        val oldPredicate = predicate(oldItem)
        val newPredicate = predicate(newItem)
        return oldPredicate == newPredicate
    }

}
