package com.pgonrod.superheroes.presentation

import androidx.recyclerview.widget.DiffUtil
import com.pgonrod.superheroes.domain.GetSuperHeroUseCase

class SuperHeroDetailImageDiffUtil: DiffUtil.ItemCallback<GetSuperHeroUseCase.SuperHeroId>() {
    override fun areItemsTheSame(
        oldItem: GetSuperHeroUseCase.SuperHeroId,
        newItem: GetSuperHeroUseCase.SuperHeroId
    ): Boolean = oldItem.imageMain == newItem.imageMain

    override fun areContentsTheSame(
        oldItem: GetSuperHeroUseCase.SuperHeroId,
        newItem: GetSuperHeroUseCase.SuperHeroId
    ): Boolean = oldItem.imageMain == newItem.imageMain
}