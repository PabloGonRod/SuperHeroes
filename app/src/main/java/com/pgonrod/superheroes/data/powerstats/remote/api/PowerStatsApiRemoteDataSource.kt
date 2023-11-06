package com.pgonrod.superheroes.data.powerstats.remote.api

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.data.ApiClient
import com.pgonrod.superheroes.data.powerstats.remote.PowerStatsRemoteDataSource
import com.pgonrod.superheroes.domain.Powerstats

class PowerStatsApiRemoteDataSource(val apiClient: ApiClient): PowerStatsRemoteDataSource {
    override suspend fun getPowerStats(heroId: Int): Either<ErrorApp, Powerstats?> {
        return apiClient.getPowerStats(heroId).map {powerStatsApiModel ->
            powerStatsApiModel!!.toDomain()
        }
    }

}