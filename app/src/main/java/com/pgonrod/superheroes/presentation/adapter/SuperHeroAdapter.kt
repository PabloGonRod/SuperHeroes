package com.pgonrod.superheroes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pgonrod.superheroes.R
import com.pgonrod.superheroes.domain.GetAllSuperHeroUseCase
import com.pgonrod.superheroes.domain.SuperHero

class SuperHeroAdapter : RecyclerView.Adapter<SuperHeroViewHolder>() {

    private val dataList: MutableList<GetAllSuperHeroUseCase.SuperHeroFeed> = mutableListOf()

    fun setDataList(superHeroes: List<GetAllSuperHeroUseCase.SuperHeroFeed>){
        dataList.clear()
        dataList.addAll(superHeroes)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_superheroe_feed, parent, false)
        return SuperHeroViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}