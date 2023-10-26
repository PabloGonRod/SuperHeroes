package com.pgonrod.superheroes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pgonrod.superheroes.R
import com.pgonrod.superheroes.domain.GetSuperHeroUseCase
import com.pgonrod.superheroes.presentation.SuperHeroDetailImageDiffUtil

class SuperHeroImageDetailAdapter: ListAdapter<GetSuperHeroUseCase.SuperHeroId, SuperHeroDetailHolder>(SuperHeroDetailImageDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroDetailHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_images_superheroes_detail, parent, false)
        return SuperHeroDetailHolder(view)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: SuperHeroDetailHolder, position: Int) {
        holder.bindDetail(currentList[position])
    }
}