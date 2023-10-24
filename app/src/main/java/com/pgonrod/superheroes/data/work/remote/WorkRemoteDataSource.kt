package com.pgonrod.superheroes.data.work.remote

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.Work

interface WorkRemoteDataSource {
    fun getWork(heroId: Int) : Either<ErrorApp, Work?>
}