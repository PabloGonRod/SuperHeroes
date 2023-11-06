package com.pgonrod.superheroes.data.powerstats

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.data.powerstats.remote.PowerStatsApiModel
import com.pgonrod.superheroes.domain.Powerstats

interface PowerStatsRemoteDataSource {

    suspend fun getPowerStats(heroId: Int) : Either<ErrorApp, Powerstats?>
}