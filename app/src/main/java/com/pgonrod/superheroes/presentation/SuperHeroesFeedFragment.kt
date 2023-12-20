package com.pgonrod.superheroes.presentation

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.pgonrod.app.data.XmlExt
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.app.extensions.errorDatabase
import com.pgonrod.app.extensions.errorInternet
import com.pgonrod.app.extensions.errorUnknown
import com.pgonrod.app.extensions.hide
import com.pgonrod.app.extensions.visible
import com.pgonrod.superheroes.MainActivity
import com.pgonrod.superheroes.R
import com.pgonrod.superheroes.data.ApiClient
import com.pgonrod.superheroes.data.biography.BiographyDataRepository
import com.pgonrod.superheroes.data.biography.local.XmlBiographyLocalDataSourceXml
import com.pgonrod.superheroes.data.biography.remote.api.BiographyApiRemoteDataSource
import com.pgonrod.superheroes.data.superhero.SuperheroesDataRepository
import com.pgonrod.superheroes.data.superhero.local.XmlSuperHeroLocalDataSource
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiRemoteDataSource
import com.pgonrod.superheroes.data.work.WorkDataRepository
import com.pgonrod.superheroes.data.work.local.XmlWorkLocalDataSource
import com.pgonrod.superheroes.data.work.remote.api.WorkApiRemoteDataSource
import com.pgonrod.superheroes.databinding.FragmentSuperHeroesFeedBinding
import com.pgonrod.superheroes.domain.GetAllSuperHeroUseCase
import com.pgonrod.superheroes.presentation.adapter.SuperHeroAdapter


class SuperHeroesFeedFragment: Fragment() {

    private var _binding: FragmentSuperHeroesFeedBinding? = null
    private val binding get() = _binding!!
    private val superheroAdapter = SuperHeroAdapter()

    private val skeleton: Skeleton by lazy {
        binding.superheroeFeed.applySkeleton(R.layout.view_item_superheroe_feed, 5)
    }

    val viewModel: SuperHeroesFeedViewModel by lazy {
        SuperHeroesFeedViewModel(
            GetAllSuperHeroUseCase(
                SuperheroesDataRepository(
                    XmlSuperHeroLocalDataSource(XmlExt(requireContext(), "SuperHeroes")),
                    SuperHeroApiRemoteDataSource(ApiClient())
                ),
                WorkDataRepository(
                    XmlWorkLocalDataSource(XmlExt(requireContext(), "Work")),
                    WorkApiRemoteDataSource(ApiClient())
                ),
                BiographyDataRepository(
                    XmlBiographyLocalDataSourceXml(XmlExt(requireContext(), "Biography")),
                    BiographyApiRemoteDataSource(ApiClient())
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperHeroesFeedBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }


    fun setupView() {
        binding.apply {
            superheroeFeed.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = superheroAdapter

            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.loadSuperheroes()
        actionSearch()
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
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    fun showError(error: ErrorApp) {
        binding.apply {
            viewError.layoutError.visible()
            superheroeFeed.hide()
        }
        when (error) {
            ErrorApp.InternetErrorApp -> errorInternet()
            ErrorApp.UnknowErrorApp -> errorUnknown()
            ErrorApp.DatabaseErrorApp -> errorDatabase()
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun errorUnknown() = binding.viewError.errorUnknown()
    fun errorDatabase() = binding.viewError.errorDatabase()
    fun errorInternet() = binding.viewError.errorInternet()

    fun navigateToDetail(id: Int) {
        val result = id
       (activity as MainActivity).changeFragment(SuperHeroesDetailFragment.newInstance())
        setFragmentResult("requestKey", bundleOf("bundleKey" to result))

    }


    private fun actionSearch() {
        binding.fab.setOnClickListener {
            binding.filter.visible()
        }

    }

}