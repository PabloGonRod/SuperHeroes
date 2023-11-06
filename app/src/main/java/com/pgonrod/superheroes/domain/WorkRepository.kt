package com.pgonrod.superheroes.domain

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp

interface WorkRepository {
    suspend fun getWork(heroId: Int) : Either<ErrorApp, Work?>
}