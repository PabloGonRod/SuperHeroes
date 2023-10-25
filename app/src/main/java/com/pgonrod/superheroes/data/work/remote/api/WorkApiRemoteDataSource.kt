package com.pgonrod.superheroes.data.work.remote.api

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.data.ApiClient
import com.pgonrod.superheroes.data.work.remote.WorkRemoteDataSource
import com.pgonrod.superheroes.domain.Work

class WorkApiRemoteDataSource(val apiClient: ApiClient): WorkRemoteDataSource {
    override suspend fun getWork(heroId: Int): Either<ErrorApp, Work?> {
        return apiClient.getWork(heroId).map { workApiModel ->
            workApiModel!!.toDomain()
        }
    }
}