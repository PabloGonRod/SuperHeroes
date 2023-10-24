package com.pgonrod.superheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pgonrod.superheroes.data.ApiClient
import com.pgonrod.superheroes.data.biography.api.BiographyApiRemoteDataSource
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiRemoteDataSource
import com.pgonrod.superheroes.data.work.remote.api.WorkApiRemoteDataSource
import com.pgonrod.superheroes.domain.SuperHeroFeed
import com.pgonrod.superheroes.domain.urlImages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        val apiClient = ApiClient()
        CoroutineScope(Dispatchers.IO).launch {
            val superheroes = SuperHeroApiRemoteDataSource(apiClient).getSuperHeroes()
            val oneSuperheroe = SuperHeroApiRemoteDataSource(apiClient).getSuperHeroe(1)
            val work = WorkApiRemoteDataSource(apiClient).getWork(1).get()
            val biography = BiographyApiRemoteDataSource(apiClient).getBiography(1).get()
            Log.d("@dev", "Superheroe: $oneSuperheroe")
            Log.d("@dev", "Lista: $superheroes")
            Log.d("@dev", "Work: $work")
            Log.d("@dev", "Biography: $biography")

            val feed = superheroes.map {
                it.map { superheroe ->
                    SuperHeroFeed(
                        superheroe.id,
                        superheroe.name,
                        work!!.occupation,
                        biography!!.fullName,
                        superheroe.images
                    )
                }
            }
            Log.d("@dev", "Lista unida: $feed")
        }
    }
}