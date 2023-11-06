package com.pgonrod.superheroes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.app.extensions.errorDatabase
import com.pgonrod.app.extensions.errorInternet
import com.pgonrod.app.extensions.errorUnknown
import com.pgonrod.app.extensions.hide
import com.pgonrod.app.extensions.visible
import com.pgonrod.superheroes.R
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

    private val skeleton: Skeleton by lazy {
        binding.superheroeFeed.applySkeleton(R.layout.view_item_superheroe_feed,5)
    }

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

            if (uiState.isloading){
                skeleton.showSkeleton()
            }else{
                skeleton.showOriginal()
                if (uiState.error != null){
                    showError(uiState.error)
                }else{
                    val list = uiState.superherolist
                    superheroAdapter.submitList(list)
                }
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    fun showError(error: ErrorApp){
        binding.apply {
            viewError.layoutError.visible()
            layoutFeed.hide()
        }
        when(error){
            ErrorApp.InternetErrorApp -> errorInternet()
            ErrorApp.UnknowErrorApp -> errorUnknown()
            ErrorApp.DatabaseErrorApp -> errorDatabase()
        }

    }
    fun errorUnknown() = binding.viewError.errorUnknown()
    fun errorDatabase() = binding.viewError.errorDatabase()
    fun errorInternet() = binding.viewError.errorInternet()


}