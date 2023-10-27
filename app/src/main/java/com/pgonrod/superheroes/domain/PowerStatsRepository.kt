package com.pgonrod.superheroes.domain

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp

interface PowerStatsRepository {

    suspend fun getPowerStats(heroId: Int) : Either<ErrorApp, Powerstats?>
}