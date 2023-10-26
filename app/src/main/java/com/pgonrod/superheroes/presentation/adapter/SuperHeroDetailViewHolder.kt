package com.pgonrod.superheroes.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.pgonrod.app.extensions.loadurl
import com.pgonrod.superheroes.databinding.ActivitySuperHeroesDetailBinding
import com.pgonrod.superheroes.domain.GetSuperHeroUseCase

class SuperHeroDetailViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    fun bindDetail(detail: GetSuperHeroUseCase.SuperHeroId){
        val itemDetail = ActivitySuperHeroesDetailBinding.bind(view)
        itemDetail.apply {
            detailName.text = detail.name
            detailFullName.text = detail.fullName
            intelillence.text = detail.intelligence.toString()
            velocity.text = detail.speed.toString()
            combat.text = detail.combat.toString()
            imageDetail.loadurl(detail.images)
            imageDetailPowerstat1.loadurl(detail.images)
            imageDetailPowerstat2.loadurl(detail.images)
            imageDetailPowerstat3.loadurl(detail.images)
        }

    }
}