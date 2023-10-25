package com.pgonrod.superheroes.domain

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiModel

interface SuperHeroRepository {
    suspend fun getAllSuperHeroes(): Either<ErrorApp, List<SuperHero>>

    suspend fun getSuperHeroe(heroId: Int) : Either<ErrorApp, SuperHero?>

}