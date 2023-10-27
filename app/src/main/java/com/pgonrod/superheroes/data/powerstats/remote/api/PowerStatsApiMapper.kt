package com.pgonrod.superheroes.data.powerstats.remote.api

import com.pgonrod.superheroes.domain.Powerstats

fun PowerStatsApiModel.toDomain(): Powerstats  = Powerstats(this.intelligence, this.speed, this.combat)