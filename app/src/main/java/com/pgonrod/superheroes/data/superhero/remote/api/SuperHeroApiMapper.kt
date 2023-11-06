package com.pgonrod.superheroes.data.superhero.remote.api

import com.pgonrod.superheroes.domain.SuperHero
import com.pgonrod.superheroes.domain.urlImages

fun SuperHeroApiModel.toDomain(): SuperHero = SuperHero(this.id, this.name, this.images.toImages())
fun Images.toImages(): urlImages = urlImages(this.xs, this.sm, this.md, this.lg)