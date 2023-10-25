package com.pgonrod.superheroes.domain

data class SuperHero(val id: Int, val name: String, val images: List<String>){
    fun getImageS(): String = images[0]
    fun getImageM(): String = images[1]
    fun getImageL(): String = images[2]
    fun getImageXL(): String = images[3]
}

data class Biography(val fullName: String)
data class Work(val occupation: String)

