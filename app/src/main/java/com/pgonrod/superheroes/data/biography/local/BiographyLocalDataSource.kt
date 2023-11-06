package com.pgonrod.superheroes.data.biography.local

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.Biography

interface BiographyLocalDataSource {

    fun saveBiography(heroId:Int,biography: Biography): Either<ErrorApp, Boolean>
    fun getBiography(heroId: Int): Either<ErrorApp, Biography?>

}