package com.pgonrod.superheroes.data

import com.pgonrod.superheroes.data.biography.remote.api.BiographyApiModel
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiModel
import com.pgonrod.superheroes.data.work.remote.api.WorkApiModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("all.json")
    fun getSuperHeroesFeed(): Call<List<SuperHeroApiModel>>

    @GET("id/{heroId}.json")
    fun getSuperHero(@Path("heroId") heroId: Int): Call<SuperHeroApiModel>

    @GET("work/{heroId}.json")
    fun getWork(@Path("heroId") heroId: Int): Call<WorkApiModel>

    @GET("biography/{heroId}.json")
    fun getbiography(@Path("heroId") heroId: Int): Call<BiographyApiModel>
}