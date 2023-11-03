package com.pgonrod.superheroes.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.widget.addTextChangedListener
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
import com.pgonrod.superheroes.data.biography.BiographyDataRepository
import com.pgonrod.superheroes.data.biography.local.XmlBiographyLocalDataSource
import com.pgonrod.superheroes.data.superhero.SuperheroesDataRepository
import com.pgonrod.superheroes.data.biography.remote.api.BiographyApiRemoteDataSource
import com.pgonrod.superheroes.data.superhero.local.XmlSuperHeroLocalDataSource
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiRemoteDataSource
import com.pgonrod.superheroes.data.work.WorkDataRepository
import com.pgonrod.superheroes.data.work.local.XmlWorkLocalDataSource
import com.pgonrod.superheroes.data.work.remote.api.WorkApiRemoteDataSource
import com.pgonrod.superheroes.databinding.ActivitySuperHeroesFeedBinding
import com.pgonrod.superheroes.domain.GetAllSuperHeroUseCase
import com.pgonrod.superheroes.presentation.adapter.SuperHeroAdapter


class SuperHeroesFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroesFeedBinding
    private val superheroAdapter = SuperHeroAdapter()

    private val skeleton: Skeleton by lazy {
        binding.superheroeFeed.applySkeleton(R.layout.view_item_superheroe_feed, 5)
    }

    val viewModel: SuperHeroesFeedViewModel by lazy {
        SuperHeroesFeedViewModel(
            GetAllSuperHeroUseCase(
                SuperheroesDataRepository(
                    XmlSuperHeroLocalDataSource(getSharedPreferences("Superheroes", MODE_PRIVATE)),
                    SuperHeroApiRemoteDataSource(ApiClient())
                ),
                WorkDataRepository(
                    XmlWorkLocalDataSource(getSharedPreferences("Work", MODE_PRIVATE)),
                    WorkApiRemoteDataSource(ApiClient())
                ),
                BiographyDataRepository(
                    XmlBiographyLocalDataSource(getSharedPreferences("Biography", MODE_PRIVATE)),
                    BiographyApiRemoteDataSource(ApiClient())
                )
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

    fun setupView() {
        binding.apply {
            superheroeFeed.apply {
                layoutManager = LinearLayoutManager(
                    this@SuperHeroesFeedActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = superheroAdapter

            }

        }
    }

    fun setupObserver() {
        val observer = Observer<SuperHeroesFeedViewModel.SuperHeroUiState> { uiState ->

            if (uiState.isloading) {
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()
                if (uiState.error != null) {
                    showError(uiState.error)
                } else {
                    val list = uiState.superherolist
                    superheroAdapter.submitList(list)
                    superheroAdapter.setOnClickDetail {
                        navigateToDetail(it)
                    }
                }
            }
            binding.filter.addTextChangedListener { filter ->
                val filtered =
                    uiState.superherolist.filter { superhero -> superhero.name.contains(filter.toString()) }
                superheroAdapter.submitList(filtered)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    fun showError(error: ErrorApp) {
        binding.apply {
            viewError.layoutError.visible()
            layoutFeed.hide()
        }
        when (error) {
            ErrorApp.InternetErrorApp -> errorInternet()
            ErrorApp.UnknowErrorApp -> errorUnknown()
            ErrorApp.DatabaseErrorApp -> errorDatabase()
        }

    }

    fun errorUnknown() = binding.viewError.errorUnknown()
    fun errorDatabase() = binding.viewError.errorDatabase()
    fun errorInternet() = binding.viewError.errorInternet()

    fun navigateToDetail(id: Int) {
        val intent = Intent(this, SuperHeroesDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    fun actionSearch(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                binding.filter.isEnabled = true
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }


}