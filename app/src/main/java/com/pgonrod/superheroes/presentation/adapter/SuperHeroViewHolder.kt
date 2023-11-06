package com.pgonrod.superheroes.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pgonrod.app.extensions.loadurl
import com.pgonrod.superheroes.databinding.ViewItemSuperheroeFeedBinding
import com.pgonrod.superheroes.domain.GetAllSuperHeroUseCase
import com.pgonrod.superheroes.domain.GetSuperHeroUseCase

class SuperHeroViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    fun bind(superhero: GetAllSuperHeroUseCase.SuperHeroFeed, onClickDetail: ((Int) -> Unit)?){
        val item = ViewItemSuperheroeFeedBinding.bind(view)
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