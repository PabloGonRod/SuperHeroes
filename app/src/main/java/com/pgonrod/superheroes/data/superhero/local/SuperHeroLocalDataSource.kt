package com.pgonrod.superheroes.data.superhero.local

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.SuperHero

interface SuperHeroLocalDataSource {

    fun saveSuperHeroes(superHero: List<SuperHero>): Either<ErrorApp, List<SuperHero>>
    fun getAllSuperHeroes(): Either<ErrorApp, List<SuperHero>>
    fun getSuperhero(superHero: SuperHero): SuperHero?
}