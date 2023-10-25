package com.pgonrod.superheroes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.pgonrod.superheroes.R
import com.pgonrod.superheroes.data.ApiClient
import com.pgonrod.superheroes.data.SuperheroesDataRepository
import com.pgonrod.superheroes.data.biography.remote.api.BiographyApiRemoteDataSource
import com.pgonrod.superheroes.data.powerstats.remote.PowerStatsApiRemoteDataSource
import com.pgonrod.superheroes.data.superhero.local.XmlSuperHeroLocalDataSource
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiRemoteDataSource
import com.pgonrod.superheroes.databinding.ActivitySuperHeroesDetailBinding
import com.pgonrod.superheroes.domain.GetSuperHeroUseCase

class SuperHeroesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroesDetailBinding

    val viewModel: SuperHeroesDetailViewModel by lazy {
        SuperHeroesDetailViewModel(
            GetSuperHeroUseCase(
                SuperheroesDataRepository(
                    XmlSuperHeroLocalDataSource(getSharedPreferences("SuperHeroes", MODE_PRIVATE)),
                    SuperHeroApiRemoteDataSource(ApiClient())
                ),
                BiographyApiRemoteDataSource(ApiClient()),
                PowerStatsApiRemoteDataSource(ApiClient()))
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObserver()
        viewModel.loadSuperHeroesDetail(2)
    }

    fun setupObserver(){
        val observerdetail = Observer<SuperHeroesDetailViewModel.SuperHeroDetailUiState>{ uiDetailState ->

            val hero = uiDetailState.superheroDetail
            Log.d("@dev", "ID: $hero")
        }

        viewModel.uidetailState.observe(this, observerdetail)
    }
}