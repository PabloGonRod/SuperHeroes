package com.pgonrod.superheroes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pgonrod.superheroes.data.ApiClient
import com.pgonrod.superheroes.data.SuperheroesDataRepository
import com.pgonrod.superheroes.data.biography.remote.api.BiographyApiRemoteDataSource
import com.pgonrod.superheroes.data.superhero.local.XmlSuperHeroLocalDataSource
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiRemoteDataSource
import com.pgonrod.superheroes.data.work.remote.api.WorkApiRemoteDataSource
import com.pgonrod.superheroes.databinding.ActivitySuperHeroesFeedBinding
import com.pgonrod.superheroes.domain.GetAllSuperHeroUseCase
import com.pgonrod.superheroes.presentation.adapter.SuperHeroAdapter


class SuperHeroesFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroesFeedBinding
    private val superheroAdapter = SuperHeroAdapter()

    val viewModel: SuperHeroesFeedViewModel by lazy {
        SuperHeroesFeedViewModel(
            GetAllSuperHeroUseCase(
                SuperheroesDataRepository(
                    XmlSuperHeroLocalDataSource(getSharedPreferences("SuperHeroes", MODE_PRIVATE)),
                    SuperHeroApiRemoteDataSource(ApiClient())
                ),
                WorkApiRemoteDataSource(ApiClient()),
                BiographyApiRemoteDataSource(ApiClient())
            )
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroesFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObserver()
        viewModel.loadSuperheroes()
        setupView()
    }

    fun setupView(){
        binding.apply {
            superheroeFeed.apply {
                layoutManager = LinearLayoutManager(
                    this@SuperHeroesFeedActivity,
                    LinearLayoutManager.VERTICAL,
                    false)
                adapter = superheroAdapter

            }

        }
    }

    fun setupObserver(){
        val observer = Observer<SuperHeroesFeedViewModel.SuperHeroUiState>{ uiState->
            val list = uiState.superherolist
            superheroAdapter.setDataList(list)
            Log.d("@dev", "lista: $list")
        }
        viewModel.uiState.observe(this, observer)
    }




}