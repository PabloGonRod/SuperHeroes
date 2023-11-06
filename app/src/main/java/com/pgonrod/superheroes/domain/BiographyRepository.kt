package com.pgonrod.superheroes.domain

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp

interface BiographyRepository {
    suspend fun getBiography(heroId: Int) : Either<ErrorApp, Biography?>
}