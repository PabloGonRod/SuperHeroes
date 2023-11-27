package com.pgonrod.superheroes.data.biography

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.data.biography.local.BiographyLocalDataSource
import com.pgonrod.superheroes.data.biography.local.XmlBiographyLocalDataSourceXml
import com.pgonrod.superheroes.data.biography.remote.BiographyRemoteDataSource
import com.pgonrod.superheroes.domain.Biography
import com.pgonrod.superheroes.domain.BiographyRepository

class BiographyDataRepository(
    private val localsource: BiographyLocalDataSource,
    private val remotesource: BiographyRemoteDataSource
): BiographyRepository {
    override suspend fun getBiography(heroId: Int): Either<ErrorApp, Biography?> {

        val biography = remotesource.getBiography(heroId)
        biography.getOrNull()?.let {biography ->
            localsource.saveBiography(heroId, biography)
        }
        return biography
    }

}