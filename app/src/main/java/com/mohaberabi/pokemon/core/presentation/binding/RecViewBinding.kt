package com.mohaberabi.pokemon.core.presentation.binding

import android.util.Log
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mohaberabi.pokemon.core.presentation.utils.AppRecyclerViewScrollListener
import com.mohaberabi.pokemon.core.presentation.utils.BaseListAdapter


object RecViewBinding {

    @JvmStatic
    @BindingAdapter(value = ["paginate", "paginateThreshold"], requireAll = false)
    fun paginateRecView(
        recView: RecyclerView,
        onLoadMore: (() -> Unit),
        threshold: Int = 4
    ) {
        recView.addOnScrollListener(
            AppRecyclerViewScrollListener(
                onPaginate = onLoadMore,
                isLinear = false,
                threshold = threshold
            )
        )
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(
        recView: RecyclerView,
        newAdapter: RecyclerView.Adapter<*>
    ) {
        recView.adapter = newAdapter
    }


    @JvmStatic
    @BindingAdapter("submitList")
    fun bindAdapterList(recView: RecyclerView, data: List<Any>?) {
        val adapter = recView.adapter
        if (adapter is ListAdapter<*, *>) {
            @Suppress("UNCHECKED_CAST")
            (adapter as ListAdapter<Any, *>).submitList(data)
        } else {
            throw Exception("Can not bind list adapter")
        }
    }

    @JvmStatic
    @BindingAdapter("paginate")
    fun paginate(recView: RecyclerView, onLoadMore: (() -> Unit)?) {
        recView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int, dy: Int
                ) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager ?: return
                    val totalItemCount = layoutManager.itemCount
                    val visibleItemCount = layoutManager.childCount
                    val firstVisibleItemPosition = when (layoutManager) {
                        is GridLayoutManager -> layoutManager.findFirstVisibleItemPosition()
                        is LinearLayoutManager -> layoutManager.findFirstVisibleItemPosition()
                        else -> return
                    }

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount > 0
                    ) {
                        onLoadMore?.invoke()
                    }
                }
            },
        )
    }
}