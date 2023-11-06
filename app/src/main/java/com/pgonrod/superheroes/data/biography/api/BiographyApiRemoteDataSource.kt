package com.pgonrod.superheroes.data.biography.api

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.data.ApiClient
import com.pgonrod.superheroes.data.biography.BiographyRemoteDataSource
import com.pgonrod.superheroes.domain.Biography

class BiographyApiRemoteDataSource(val apiClient: ApiClient): BiographyRemoteDataSource {
    override fun getBiography(heroId: Int): Either<ErrorApp, Biography?> {
        return apiClient.getBiography(heroId).map { biographyApiModel ->
            biographyApiModel!!.toDomain()
        }
    }
}