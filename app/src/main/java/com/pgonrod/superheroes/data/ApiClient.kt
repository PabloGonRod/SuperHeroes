package com.pgonrod.superheroes.data

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.app.errors.left
import com.pgonrod.app.errors.right
import com.pgonrod.superheroes.data.biography.remote.api.BiographyApiModel
import com.pgonrod.superheroes.data.powerstats.remote.api.PowerStatsApiModel
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiModel
import com.pgonrod.superheroes.data.work.remote.api.WorkApiModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
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

    suspend fun getSuperHeroes(): Either<ErrorApp, List<SuperHeroApiModel>>  {

        try {
            val superheroes = apiService.getSuperHeroesFeed()

            if (superheroes.isSuccessful){
                return superheroes.body()!!.right()
            } else {
                return ErrorApp.InternetErrorApp.left()
            }
        }catch (ex: TimeoutException){
            return ErrorApp.InternetErrorApp.left()
        }catch (ex: UnknownHostException){
            return ErrorApp.InternetErrorApp.left()
        }catch (ex: RuntimeException){
            return ErrorApp.InternetErrorApp.left()
        }

    }

    suspend fun getSuperHeroe(heroId: Int) : Either<ErrorApp, SuperHeroApiModel?>{
        val call = apiService.getSuperHero(heroId)
        return call.body().right()
    }

    suspend fun getWork(heroId: Int) : Either<ErrorApp, WorkApiModel?>{
        val call = apiService.getWork(heroId)
        return call.body().right()
    }

    suspend fun getBiography(heroId: Int) : Either<ErrorApp, BiographyApiModel?>{
        val call = apiService.getbiography(heroId)
        return call.body().right()
    }

    suspend fun getPowerStats(heroId: Int) : Either<ErrorApp, PowerStatsApiModel?>{
        val call = apiService.getpowerstats(heroId)
        return call.body().right()
    }



}