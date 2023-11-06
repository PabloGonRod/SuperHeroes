package com.pgonrod.superheroes.data.superhero.remote.api

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.data.ApiClient
import com.pgonrod.superheroes.data.superhero.remote.SuperHeroRemoteDataSource
import com.pgonrod.superheroes.domain.SuperHero

class SuperHeroApiRemoteDataSource(val apiClient: ApiClient): SuperHeroRemoteDataSource {
    override suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHero>> {
        return apiClient.getSuperHeroes().map { listSuperheroApiModel ->
            listSuperheroApiModel.subList(0,100).map { superheroeApiModel ->
                superheroeApiModel.toDomain()
            }
        }
    }

    override suspend fun getSuperHeroe(heroId: Int): Either<ErrorApp, SuperHero?> {
        return apiClient.getSuperHeroe(heroId).map { superheroe ->
            superheroe!!.toDomain()
        }
    }
}