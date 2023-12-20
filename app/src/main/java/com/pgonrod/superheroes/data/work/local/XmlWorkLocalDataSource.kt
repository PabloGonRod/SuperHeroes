package com.pgonrod.superheroes.data.work.local

import android.content.Context
import android.content.SharedPreferences
import com.pgonrod.app.data.XmlExt
import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.Powerstats
import com.pgonrod.superheroes.domain.SuperHero
import com.pgonrod.superheroes.domain.Work

class XmlWorkLocalDataSource(val generic: XmlExt): WorkLocalDataSource {


    override fun saveWork(heroId: Int, work: Work): Either<ErrorApp, Boolean> {
        return generic.save(heroId, work)
    }

    override fun getWork(heroId: Int): Either<ErrorApp, Work?> {
        return generic.getGenericById(heroId)
    }


}