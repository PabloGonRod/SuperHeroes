package com.pgonrod.superheroes.data.superhero.remote.api

import com.pgonrod.superheroes.domain.SuperHero

fun SuperHeroApiModel.toDomain(): SuperHero = SuperHero(this.id, this.name, listOf(
    this.images.sm))
