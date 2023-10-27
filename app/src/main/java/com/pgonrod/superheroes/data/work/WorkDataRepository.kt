package com.pgonrod.superheroes.data.work

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.data.work.local.WorkLocalDataSource
import com.pgonrod.superheroes.data.work.remote.WorkRemoteDataSource
import com.pgonrod.superheroes.domain.Work
import com.pgonrod.superheroes.domain.WorkRepository

class WorkDataRepository(
    private val localsource: WorkLocalDataSource,
    private val remotesource: WorkRemoteDataSource
): WorkRepository {
    override suspend fun getWork(heroId: Int): Either<ErrorApp, Work?> {
        val work = localsource.getWork(heroId)
        return if (work.isLeft()){
            remotesource.getWork(heroId).map { superheroes ->
                localsource.saveWork(heroId, superheroes!!)
                superheroes
            }
        } else{
            work
        }
    }
}