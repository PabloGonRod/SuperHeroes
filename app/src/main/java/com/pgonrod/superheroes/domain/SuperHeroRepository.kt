package com.pgonrod.superheroes.domain

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp

interface SuperHeroRepository {
    fun getAllSuperHeroes(): Either<ErrorApp, List<SuperHero>>
}