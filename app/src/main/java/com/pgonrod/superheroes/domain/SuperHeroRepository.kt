package com.pgonrod.superheroes.domain

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp

interface SuperHeroRepository {
    suspend fun getAllSuperHeroes(): Either<ErrorApp, List<SuperHero>>
}