package com.pgonrod.superheroes.presentation

import androidx.recyclerview.widget.DiffUtil
import com.pgonrod.superheroes.domain.GetAllSuperHeroUseCase

class SuperHeroDiffUtil : DiffUtil.ItemCallback<GetAllSuperHeroUseCase.SuperHeroFeed>() {
    override fun areItemsTheSame(
        oldItem: GetAllSuperHeroUseCase.SuperHeroFeed,
        newItem: GetAllSuperHeroUseCase.SuperHeroFeed
    ): Boolean = oldItem.id == newItem.id


    override fun areContentsTheSame(
        oldItem: GetAllSuperHeroUseCase.SuperHeroFeed,
        newItem: GetAllSuperHeroUseCase.SuperHeroFeed
    ): Boolean = oldItem == newItem

}