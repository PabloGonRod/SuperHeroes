package com.pgonrod.superheroes.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pgonrod.app.extensions.loadurl
import com.pgonrod.superheroes.databinding.ViewItemImagesSuperheroesDetailBinding
import com.pgonrod.superheroes.domain.GetSuperHeroUseCase

class SuperHeroDetailHolder(view: View): RecyclerView.ViewHolder(view) {

    val img = ViewItemImagesSuperheroesDetailBinding.bind(view)
    fun bindDetail(image: String){
        img.imageDetailPowerstat1.loadurl(image)
    }
}