package com.pgonrod.superheroes.data.powerstats.local

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.Powerstats

interface PowerstatsLocalDataSource {

    fun savePowerstats(heroId:Int,powerstats: Powerstats): Either<ErrorApp, Boolean>
    fun getPowerstats(heroId: Int): Either<ErrorApp, Powerstats?>
}