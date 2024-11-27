package com.mohaberabi.pokemon.core.presentation.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.mohaberabi.pokemon.R
import com.mohaberabi.pokemon.databinding.ProgressViewBinding

class PokemonProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ProgressViewBinding

    init {
        val inflater = LayoutInflater.from(context)
        binding = ProgressViewBinding.inflate(inflater, this, true)

        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.PokemonProgressView)
            binding.typeLabel =
                attributes.getString(R.styleable.PokemonProgressView_typeLabel) ?: ""
            binding.progress = attributes.getInt(R.styleable.PokemonProgressView_progress, 0)
            binding.maxProgress =
                attributes.getInt(R.styleable.PokemonProgressView_maxProgress, 100)
            binding.progressColor =
                attributes.getColor(R.styleable.PokemonProgressView_progressColor, Color.BLUE)
            attributes.recycle()
        }
    }

    fun setLabel(label: String) {
        binding.typeLabel = label
    }

    fun setProgress(progress: Int) {
        binding.progress = progress
    }

    fun setMaxProgress(maxProgress: Int) {
        binding.maxProgress = maxProgress
    }

    fun setProgressColor(color: Int) {
        binding.progressColor = color
    }
}