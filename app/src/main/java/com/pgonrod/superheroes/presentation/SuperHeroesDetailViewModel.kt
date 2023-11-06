package com.pgonrod.superheroes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.GetSuperHeroUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroesDetailViewModel(private val useCase: GetSuperHeroUseCase) : ViewModel() {

    private val _uidetailState = MutableLiveData<SuperHeroDetailUiState>()
    val uidetailState: LiveData<SuperHeroDetailUiState> = _uidetailState

    fun loadSuperHeroesDetail(heroId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val detail = useCase.invoke(heroId)
            detail.fold(
                { responseError(it) },
                { _uidetailState.postValue(
                    SuperHeroDetailUiState(
                        superheroDetail = detail.get())) }
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) =
        _uidetailState.postValue(SuperHeroDetailUiState(error = errorApp))

    data class SuperHeroDetailUiState(
        val error: ErrorApp? = null,
        val superheroDetail: GetSuperHeroUseCase.SuperHeroId? = null
    )

}