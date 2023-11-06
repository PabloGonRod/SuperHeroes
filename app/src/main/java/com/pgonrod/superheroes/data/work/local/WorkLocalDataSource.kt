package com.pgonrod.superheroes.data.work.local

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.Work

interface WorkLocalDataSource {
    fun saveWork(heroId:Int,work: Work): Either<ErrorApp, Boolean>
    fun getWork(heroId: Int): Either<ErrorApp, Work?>
}