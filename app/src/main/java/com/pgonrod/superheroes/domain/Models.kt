package com.pgonrod.superheroes.domain

data class SuperHero(val id: Int, val name: String, val images: urlImages)
data class urlImages(val xs: String, val sm: String, val md: String, val lg: String)
data class Biography(val fullName: String)
data class Work(val occupation: String)


data class SuperHeroFeed(
    val id: Int,
    val name: String,
    val occupation: String,
    val realName: String
)
