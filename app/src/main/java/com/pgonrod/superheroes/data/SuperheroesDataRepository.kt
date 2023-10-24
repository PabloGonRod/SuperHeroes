package com.pgonrod.superheroes.data

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.data.superhero.local.SuperHeroLocalDataSource
import com.pgonrod.superheroes.data.superhero.local.XmlSuperHeroLocalDataSource
import com.pgonrod.superheroes.data.superhero.remote.SuperHeroRemoteDataSource
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiRemoteDataSource
import com.pgonrod.superheroes.domain.SuperHero
import com.pgonrod.superheroes.domain.SuperHeroRepository

class SuperheroesDataRepository (
    private val localsource: XmlSuperHeroLocalDataSource,
    private val remotesource: SuperHeroApiRemoteDataSource
): SuperHeroRepository {
    override fun getAllSuperHeroes(): Either<ErrorApp, List<SuperHero>> {
        val list = localsource.getAllSuperHeroes()
        return if (list.isLeft() || list.get().isEmpty()){
                remotesource.getSuperHeroes().map { superheroes ->
                localsource.saveSuperHeroes(superheroes)
                superheroes
            }
        } else{
            list
        }
    }
}