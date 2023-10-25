package com.pgonrod.superheroes.data.superhero.remote.api

import com.pgonrod.superheroes.domain.SuperHero
import com.pgonrod.superheroes.domain.urlImage

fun SuperHeroApiModel.toDomain(): SuperHero = SuperHero(this.id, this.name, listOf(
    this.images.xs, this.images.sm, this.images.md, this.images.lg
))


