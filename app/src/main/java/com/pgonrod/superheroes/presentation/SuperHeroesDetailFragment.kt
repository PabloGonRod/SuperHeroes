package com.pgonrod.superheroes.presentation

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pgonrod.app.data.XmlExt
import com.pgonrod.app.extensions.loadurl
import com.pgonrod.superheroes.data.ApiClient
import com.pgonrod.superheroes.data.biography.BiographyDataRepository
import com.pgonrod.superheroes.data.biography.local.XmlBiographyLocalDataSourceXml
import com.pgonrod.superheroes.data.biography.remote.api.BiographyApiRemoteDataSource
import com.pgonrod.superheroes.data.powerstats.PowerStatsDataRepository
import com.pgonrod.superheroes.data.powerstats.local.XmlPowerStatsLocalDataSource
import com.pgonrod.superheroes.data.powerstats.remote.api.PowerStatsApiRemoteDataSource
import com.pgonrod.superheroes.data.superhero.SuperheroesDataRepository
import com.pgonrod.superheroes.data.superhero.local.XmlSuperHeroLocalDataSource
import com.pgonrod.superheroes.data.superhero.remote.api.SuperHeroApiRemoteDataSource
import com.pgonrod.superheroes.databinding.FragmentSuperHeroesDetailBinding
import com.pgonrod.superheroes.domain.GetSuperHeroUseCase
import com.pgonrod.superheroes.domain.SuperHero
import com.pgonrod.superheroes.presentation.adapter.SuperHeroImageDetailAdapter

class SuperHeroesDetailFragment : Fragment() {

    private var _binding: FragmentSuperHeroesDetailBinding? = null
    private val binding get() = _binding!!

    private val detailAdapter = SuperHeroImageDetailAdapter()


    val viewModel: SuperHeroesDetailViewModel by lazy {
        SuperHeroesDetailViewModel(
            GetSuperHeroUseCase(
                SuperheroesDataRepository(
                    XmlSuperHeroLocalDataSource(XmlExt(requireContext(), "SuperHeroes")),
                    SuperHeroApiRemoteDataSource(ApiClient())
                ),
                BiographyDataRepository(
                    XmlBiographyLocalDataSourceXml(XmlExt(requireContext(), "Biography")),
                    BiographyApiRemoteDataSource(ApiClient())
                ),
                PowerStatsDataRepository(
                    XmlPowerStatsLocalDataSource(XmlExt(requireContext(), "PowerStats")),
                    PowerStatsApiRemoteDataSource(ApiClient())
                )

            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperHeroesDetailBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setFragmentResultListener("requestKey"){requestKey, bundle ->
            val result = bundle.getInt("bundleKey")
            viewModel.loadSuperHeroesDetail(result)
        }
    }

    fun setupView(){
        binding.apply {
            imagelist.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
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

        viewModel.uidetailState.observe(viewLifecycleOwner, observerdetail)
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

    companion object {
        fun newInstance()= SuperHeroesDetailFragment()
    }
}
