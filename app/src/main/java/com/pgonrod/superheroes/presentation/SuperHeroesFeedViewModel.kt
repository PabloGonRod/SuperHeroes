package com.pgonrod.superheroes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.GetAllSuperHeroUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuperHeroesFeedViewModel(private val useCase: GetAllSuperHeroUseCase): ViewModel() {

    private val _uistate = MutableLiveData<SuperHeroUiState>()
    val uiState: LiveData<SuperHeroUiState> = _uistate

    fun loadSuperheroes(){
        _uistate.postValue(SuperHeroUiState(isloading = true))
        viewModelScope.launch (Dispatchers.IO) {
            val feed = useCase.invoke()
            delay(2000)
            feed.fold(
                {responseError(it)},
                {_uistate.postValue(
                    SuperHeroUiState(
                        superherolist = feed.get()))}
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) = _uistate.postValue(SuperHeroUiState(error = errorApp))

    data class SuperHeroUiState(
        val isloading: Boolean = false,
        val error: ErrorApp? = null,
        val superherolist: List<GetAllSuperHeroUseCase.SuperHeroFeed> = emptyList()
    )

    /**/
}