package com.pgonrod.superheroes.data

import com.pgonrod.superheroes.data.biography.remote.api.BiographyApiModel
import com.pgonrod.superheroes.data.powerstats.remote.api.PowerStatsApiModel
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiModel
import com.pgonrod.superheroes.data.work.remote.api.WorkApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("all.json")
    suspend fun getSuperHeroesFeed(): Response<List<SuperHeroApiModel>>

    @GET("id/{heroId}.json")
    suspend fun getSuperHero(@Path("heroId") heroId: Int): Response<SuperHeroApiModel>

    @GET("work/{heroId}.json")
    suspend fun getWork(@Path("heroId") heroId: Int): Response<WorkApiModel>

    @GET("biography/{heroId}.json")
    suspend fun getbiography(@Path("heroId") heroId: Int): Response<BiographyApiModel>

    @GET("powerstats/{heroId}.json")
    suspend fun getpowerstats(@Path("heroId") heroId: Int): Response<PowerStatsApiModel>
}