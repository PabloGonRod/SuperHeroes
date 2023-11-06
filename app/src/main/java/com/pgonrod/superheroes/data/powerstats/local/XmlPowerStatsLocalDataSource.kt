package com.pgonrod.superheroes.data.powerstats.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.app.errors.left
import com.pgonrod.app.errors.right
import com.pgonrod.superheroes.domain.Biography
import com.pgonrod.superheroes.domain.Powerstats
import com.pgonrod.superheroes.domain.Work

class XmlPowerStatsLocalDataSource(val sharedPref: SharedPreferences) {

    private val editor = sharedPref.edit()
    private val gson = Gson()

    fun savePowerstats(heroId:Int,powerstats: Powerstats): Either<ErrorApp, Boolean> {
        try {
            val jsonPowerstats = gson.toJson(powerstats, Powerstats::class.java)
            editor.putString(heroId.toString(),jsonPowerstats)
            editor.apply()
        }catch (ex: Exception){
            return ErrorApp.DatabaseErrorApp.left()
        }
        return true.right()
    }

    fun getPowerstats(heroId: Int): Either<ErrorApp, Powerstats?> {
        try {
            val powerstats = sharedPref.getString(heroId.toString(), null).let {
                gson.fromJson(it, Powerstats::class.java)
            }
            return powerstats.right()

        }catch (ex:Exception){
            return ErrorApp.DatabaseErrorApp.left()
        }
    }
}