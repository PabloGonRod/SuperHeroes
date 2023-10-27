package com.pgonrod.superheroes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pgonrod.superheroes.R
import com.pgonrod.superheroes.domain.GetSuperHeroUseCase
import com.pgonrod.superheroes.presentation.SuperHeroDetailImageDiffUtil

class SuperHeroImageDetailAdapter: RecyclerView.Adapter<SuperHeroDetailHolder>() {

    private val dataItems = mutableListOf<String>()

    fun setDataItems(imageHero: List<String>){
        dataItems.addAll(imageHero)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroDetailHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_images_superheroes_detail, parent, false)
        return SuperHeroDetailHolder(view)
    }

    override fun getItemCount(): Int = dataItems.size

    override fun onBindViewHolder(holder: SuperHeroDetailHolder, position: Int) {
        holder.bindDetail(dataItems[position])
    }
}