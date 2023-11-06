package com.pgonrod.superheroes.data

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.app.errors.left
import com.pgonrod.app.errors.right
import com.pgonrod.superheroes.data.biography.api.BiographyApiModel
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiModel
import com.pgonrod.superheroes.data.work.remote.api.WorkApiModel
import com.pgonrod.superheroes.domain.SuperHero
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeoutException

class ApiClient {

    private val baseEndPoint: String = "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/"

    private val apiService: ApiService

    init {
        apiService = buildApiEndPoints()
    }

    fun createRetrofitClient() = Retrofit.Builder()
        .baseUrl(baseEndPoint)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    fun buildApiEndPoints() = createRetrofitClient().create(ApiService::class.java)

    fun getSuperHeroes(): Either<ErrorApp, List<SuperHeroApiModel>>  {
            val superheroes = apiService.getSuperHeroesFeed()
            val response = superheroes.execute()
            if (response.isSuccessful){
                return response.body()!!.right()
            } else {
                return ErrorApp.InternetErrorApp.left()
            }
    }

    fun getSuperHeroe(heroId: Int) : Either<ErrorApp, SuperHeroApiModel?>{
        val call = apiService.getSuperHero(heroId).execute()
        return call.body().right()
    }

    fun getWork(heroId: Int) : Either<ErrorApp, WorkApiModel?>{
        val call = apiService.getWork(heroId).execute()
        return call.body().right()
    }

    fun getBiography(heroId: Int) : Either<ErrorApp, BiographyApiModel?>{
        val call = apiService.getbiography(heroId).execute()
        return call.body().right()
    }

}