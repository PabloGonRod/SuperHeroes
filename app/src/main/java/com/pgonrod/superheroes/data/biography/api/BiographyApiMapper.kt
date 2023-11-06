package com.pgonrod.superheroes.data.biography.api

import com.pgonrod.superheroes.domain.Biography

fun BiographyApiModel.toDomain(): Biography = Biography(this.fullName)