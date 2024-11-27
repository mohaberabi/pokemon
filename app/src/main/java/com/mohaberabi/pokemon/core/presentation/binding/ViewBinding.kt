package com.mohaberabi.pokemon.core.presentation.binding

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.card.MaterialCardView
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.mohaberabi.pokemon.R
import kotlin.math.max

@BindingAdapter("imageUrl", "bgCard", requireAll = false)
fun ImageView.bindImageUrl(
    imageUrl: String?,
    bgCard: View? = null
) {
    this.load(imageUrl) {
        placeholder(R.drawable.img_pl)
        error(R.drawable.img_pl)
        allowHardware(false)
        listener(
            onSuccess = { _, result ->
                val drawable = result.drawable
                if (drawable is BitmapDrawable) {
                    val bitmap = drawable.bitmap
                    Palette.Builder(bitmap).generate { palette ->
                        palette?.dominantSwatch?.rgb?.let { rgb ->
                            setBackgroundColor(rgb)
                            bgCard?.setBackgroundColor(rgb)
                        }
                    }
                }
            },
            onError = { _, _ -> }
        )
    }
}

@BindingAdapter("symbolValue", "sign")
fun TextView.bindSymbolText(
    symbolValue: Number,
    sign: String,
) {

    text = context.getString(R.string.symbol_text, symbolValue.toString(), sign)
}

@BindingAdapter("showIf")
fun View.bindShowIf(
    showIf: Boolean,
) {
    visibility = if (showIf) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("customColor")
fun setIndicatorColor(indicator: LinearProgressIndicator, customColor: Int) {
    indicator.setIndicatorColor(customColor)
}

@BindingAdapter("minProgress", "maxProgress", requireAll = true)
fun TextView.bindProgress(
    minProgress: Int,
    maxProgress: Int,
) {

    text = context.getString(R.string.progress, minProgress.toString(), maxProgress.toString())
}
