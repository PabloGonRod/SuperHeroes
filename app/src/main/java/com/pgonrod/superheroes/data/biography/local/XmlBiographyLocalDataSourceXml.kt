package com.pgonrod.superheroes.data.biography.local

import android.content.SharedPreferences
import com.pgonrod.app.data.XmlExt

import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.Biography
import com.pgonrod.superheroes.domain.SuperHero


class XmlBiographyLocalDataSourceXml: BiographyLocalDataSource{

    private lateinit var generic: XmlExt<Biography>

    override fun saveBiography(heroId: Int, biography: Biography): Either<ErrorApp, Boolean> {
        generic
        return generic.save(heroId, biography)


    }

    override fun getBiography(heroId: Int): Either<ErrorApp, Biography?> {
        generic
        return generic.getGenericById(heroId)
    }


}


/*private val editor = sharedpref.edit()
    private val gson = Gson()

    fun saveBiography(heroId:Int,biography: Biography): Either<ErrorApp, Boolean> {
        try {
            val jsonBiography = gson.toJson(biography, Biography::class.java)
            editor.putString(heroId.toString(),jsonBiography)
            editor.apply()
        }catch (ex: Exception){
            return ErrorApp.DatabaseErrorApp.left()
        }
        return true.right()
    }

    fun getBiography(heroId: Int): Either<ErrorApp, Biography?> {
        return try {
            val biography = sharedpref.getString(heroId.toString(), null).let {
                gson.fromJson(it, Biography::class.java)
            }
            biography.right()

        }catch (ex:Exception){
            ErrorApp.DatabaseErrorApp.left()
        }
    }*/