package com.pgonrod.superheroes.data.work.local

import android.content.SharedPreferences
import com.pgonrod.app.data.XmlExt
import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.Powerstats
import com.pgonrod.superheroes.domain.Work

class XmlWorkLocalDataSource(): WorkLocalDataSource {

    private lateinit var generic: XmlExt<Powerstats>
    override fun saveWork(heroId: Int, work: Work): Either<ErrorApp, Boolean> {
        generic
        return generic.save(heroId, work)
    }

    override fun getWork(heroId: Int): Either<ErrorApp, Work?> {
        generic
        return generic.getGenericById(heroId)
    }


}