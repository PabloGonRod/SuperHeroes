package com.pgonrod.superheroes.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.pgonrod.app.extensions.loadurl
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
        val id = intent.extras!!.getInt("id")
        viewModel.loadSuperHeroesDetail(id)
    }

    fun setupObserver(){
        val observerdetail = Observer<SuperHeroesDetailViewModel.SuperHeroDetailUiState>{ uiDetailState ->

            val detail = uiDetailState.superheroDetail!!
            bindDetail(detail)

            Log.d("@dev", "ID: $detail")
        }

        viewModel.uidetailState.observe(this, observerdetail)
    }


    private fun bindDetail(detail: GetSuperHeroUseCase.SuperHeroId){
        binding.apply {
            detailName.text = detail.name
            detailFullName.text = detail.fullName
            numIntelillence.text = detail.intelligence.toString()
            numVelocity.text = detail.speed.toString()
            numCombat.text = detail.combat.toString()
            imageDetail.loadurl(detail.images)
            imageDetailPowerstat1.loadurl(detail.images)
            imageDetailPowerstat2.loadurl(detail.images)
            imageDetailPowerstat3.loadurl(detail.images)
        }

    }
}
