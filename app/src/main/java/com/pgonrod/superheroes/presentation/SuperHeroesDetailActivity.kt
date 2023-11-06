package com.pgonrod.superheroes.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.pgonrod.superheroes.presentation.adapter.SuperHeroImageDetailAdapter

class SuperHeroesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroesDetailBinding

    private val detailAdapter = SuperHeroImageDetailAdapter()


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
        setupView()
    }

    fun setupView(){
        binding.apply {
            imagelist.apply {
                layoutManager = LinearLayoutManager(
                    this@SuperHeroesDetailActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = detailAdapter
            }
        }
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
            imageDetail.loadurl(detail.imageMain)
            detailAdapter.setDataItems(detail.images)
        }

    }
}
