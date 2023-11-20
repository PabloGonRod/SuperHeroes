package com.pgonrod.presentation

import androidx.recyclerview.widget.DiffUtil

import com.pgonrod.superheroes.domain.GetAllSuperHeroUseCase
import com.pgonrod.superheroes.domain.Powerstats
import com.pgonrod.superheroes.domain.SuperHero


class DiffUtilExt<T>(
    private val areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
    private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return areItemsTheSame.invoke(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return areContentsTheSame.invoke(oldItem, newItem)
    }


}
