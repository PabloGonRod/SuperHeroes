package com.pgonrod.superheroes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pgonrod.superheroes.R
import com.pgonrod.superheroes.domain.GetAllSuperHeroUseCase
import com.pgonrod.superheroes.domain.SuperHero
import com.pgonrod.superheroes.presentation.SuperHeroDiffUtil

class SuperHeroAdapter : ListAdapter<GetAllSuperHeroUseCase.SuperHeroFeed, SuperHeroViewHolder>(SuperHeroDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_superheroe_feed, parent, false)
        return SuperHeroViewHolder(view)
    }
    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}