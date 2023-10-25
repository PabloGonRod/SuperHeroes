package com.pgonrod.superheroes.domain

data class SuperHero(val id: Int, val name: String, val images: List<String>){
    fun getUrlImageS(): String = images[0]
    fun getUrlImagesM(): String = images[1]
    fun getUrlImagesL(): String = images[2]
    fun getUrlImagesXL(): String = images[3]
}
data class urlImage(val md: String)
data class Biography(val fullName: String)
data class Work(val occupation: String)

data class Powerstats(val intelligence: Int, val speed: Int, val combat: Int)

