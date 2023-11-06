package com.pgonrod.superheroes.data.biography.remote

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.Biography

interface BiographyRemoteDataSource {
    suspend fun getBiography(heroId: Int) : Either<ErrorApp, Biography?>

}