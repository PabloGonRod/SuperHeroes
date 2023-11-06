package com.pgonrod.superheroes.domain

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.app.errors.right
import com.pgonrod.superheroes.data.biography.remote.BiographyRemoteDataSource
import com.pgonrod.superheroes.data.biography.remote.api.BiographyApiRemoteDataSource
import com.pgonrod.superheroes.data.work.remote.WorkRemoteDataSource
import com.pgonrod.superheroes.data.work.remote.api.WorkApiRemoteDataSource

class GetAllSuperHeroUseCase (
    private val superheroRepository: SuperHeroRepository,
    private val workRepository: WorkRepository,
    private val biographyRepository: BiographyRepository) {
    suspend operator fun invoke(): Either<ErrorApp, List<SuperHeroFeed>> {
        val superheroes = superheroRepository.getAllSuperHeroes()

        val list = superheroes.map {
            it.map { superhero ->
                val work = workRepository.getWork(superhero.id).get()
                val biography = biographyRepository.getBiography(superhero.id).get()

                SuperHeroFeed(
                    superhero.id,
                    superhero.name,
                    superhero.getUrlImagesM(),
                    work!!.occupation,
                    biography!!.fullName
                )
            }
        }
        return list
    }


    data class SuperHeroFeed(
        val id: Int,
        val name: String,
        val images: String,
        val occupation: String,
        val fullName: String)
}