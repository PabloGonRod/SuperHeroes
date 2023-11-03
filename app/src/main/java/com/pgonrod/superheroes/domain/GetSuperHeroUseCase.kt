package com.pgonrod.superheroes.domain

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.app.errors.right
import com.pgonrod.superheroes.data.biography.remote.api.BiographyApiRemoteDataSource
import com.pgonrod.superheroes.data.powerstats.remote.api.PowerStatsApiRemoteDataSource

class GetSuperHeroUseCase(
    private val superheroRepository: SuperHeroRepository,
    private val biographyRepository: BiographyRepository,
    private val powerStatsRepository: PowerStatsRepository
) {

    suspend operator fun invoke(heroId: Int): Either<ErrorApp, SuperHeroId> {
        val hero = superheroRepository.getSuperHeroe(heroId).get()
        val biography = biographyRepository.getBiography(heroId).get()
        val powerstats = powerStatsRepository.getPowerStats(heroId).get()
        return SuperHeroId(
            hero!!.name,
            biography!!.fullName,
            powerstats!!.intelligence,
            powerstats.speed,
            powerstats.combat,
            hero.getUrlImagesXL(),
            hero.images
        ).right()
    }

    data class SuperHeroId(
        val name: String,
        val fullName: String,
        val intelligence: Int,
        val speed: Int,
        val combat: Int,
        val imageMain: String,
        val images: List<String>
    )
}