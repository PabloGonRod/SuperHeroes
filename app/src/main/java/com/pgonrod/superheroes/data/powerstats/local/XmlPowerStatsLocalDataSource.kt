package com.pgonrod.superheroes.data.powerstats.local

import android.content.SharedPreferences
import com.pgonrod.app.data.XmlExt
import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.Biography
import com.pgonrod.superheroes.domain.Powerstats

class XmlPowerStatsLocalDataSource(): PowerstatsLocalDataSource {

    private lateinit var generic: XmlExt<Powerstats>


    override fun savePowerstats(heroId: Int, powerstats: Powerstats): Either<ErrorApp, Boolean> {
        generic
        return generic.save(heroId, powerstats)
    }

    override fun getPowerstats(heroId: Int): Either<ErrorApp, Powerstats?> {
        generic
        return generic.getGenericById(heroId)
    }


}