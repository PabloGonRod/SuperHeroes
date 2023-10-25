package com.pgonrod.superheroes.data.superhero.remote

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiModel
import com.pgonrod.superheroes.domain.SuperHero

interface SuperHeroRemoteDataSource {
    suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHero>>

    suspend fun getSuperHeroe(heroId: Int) : Either<ErrorApp, SuperHero?>
}