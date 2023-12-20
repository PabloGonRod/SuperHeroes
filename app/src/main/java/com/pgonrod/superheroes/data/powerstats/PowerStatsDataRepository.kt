package com.pgonrod.superheroes.data.powerstats

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.data.powerstats.local.PowerstatsLocalDataSource
import com.pgonrod.superheroes.data.powerstats.local.XmlPowerStatsLocalDataSource
import com.pgonrod.superheroes.data.powerstats.remote.PowerStatsRemoteDataSource
import com.pgonrod.superheroes.domain.PowerStatsRepository
import com.pgonrod.superheroes.domain.Powerstats
class PowerStatsDataRepository(
    private val localsource: PowerstatsLocalDataSource,
    private val remotesource: PowerStatsRemoteDataSource
):PowerStatsRepository {
    override suspend fun getPowerStats(heroId: Int): Either<ErrorApp, Powerstats?> {

        val powerstats = remotesource.getPowerStats(heroId)
        powerstats.getOrNull()?.let { powerstats ->
            localsource.savePowerstats(heroId, powerstats)
        }
        return powerstats

    }
}