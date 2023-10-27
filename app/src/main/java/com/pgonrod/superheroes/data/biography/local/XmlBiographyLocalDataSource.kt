package com.pgonrod.superheroes.data.biography.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.app.errors.left
import com.pgonrod.app.errors.right
import com.pgonrod.superheroes.domain.Biography
import com.pgonrod.superheroes.domain.Work

class XmlBiographyLocalDataSource (val sharedpref: SharedPreferences) {

    private val editor = sharedpref.edit()
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
        try {
            val biography = sharedpref.getString(heroId.toString(), null).let {
                gson.fromJson(it, Biography::class.java)
            }
            return biography.right()

        }catch (ex:Exception){
            return ErrorApp.DatabaseErrorApp.left()
        }
    }
}