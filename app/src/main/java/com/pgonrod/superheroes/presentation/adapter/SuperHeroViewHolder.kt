package com.pgonrod.superheroes.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pgonrod.app.extensions.loadurl
import com.pgonrod.superheroes.databinding.ViewItemSuperheroeFeedBinding
import com.pgonrod.superheroes.domain.GetAllSuperHeroUseCase
import com.pgonrod.superheroes.domain.GetSuperHeroUseCase

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val item = ViewItemSuperheroeFeedBinding.bind(view)
    fun bind(superhero: GetAllSuperHeroUseCase.SuperHeroFeed, onClickDetail: ((Int) -> Unit)?){

        item.apply {
            image.loadurl(superhero.images)
            name.text = superhero.name
            fullName.text = superhero.fullName
            occupation.text = superhero.occupation
            icArrow.setOnClickListener {
                onClickDetail!!.invoke(superhero.id)
            }

        }
    }
}