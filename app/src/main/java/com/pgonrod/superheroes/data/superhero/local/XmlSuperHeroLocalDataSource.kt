package com.pgonrod.superheroes.data.superhero.local

import android.content.LocusId
import android.content.SharedPreferences
import com.google.gson.Gson
import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.app.errors.left
import com.pgonrod.app.errors.right
import com.pgonrod.superheroes.domain.SuperHero

class XmlSuperHeroLocalDataSource (private val sharedPref: SharedPreferences) {

    private val editor = sharedPref.edit()
    private val gson = Gson()
    fun saveSuperHeroes(superHero: List<SuperHero>): Either<ErrorApp, List<SuperHero>> {
        return try {
            superHero.forEach{
                editor.putString(it.id.toString(), gson.toJson(superHero))
            }
            editor.apply()
            superHero.right()
        } catch (ex: Exception){
            ErrorApp.DatabaseErrorApp.left()
        }
    }

    fun getAllSuperHeroes(): Either<ErrorApp, List<SuperHero>>{
        return try {
            val superheroes: MutableList<SuperHero> = mutableListOf()
            sharedPref.all.forEach { map ->
                superheroes.add(gson.fromJson(map.value as String, SuperHero::class.java))
            }
            superheroes.right()
        } catch (ex: Exception){
            ErrorApp.DatabaseErrorApp.left()
        }
    }

    fun getSuperhero(superHero: SuperHero): SuperHero?{
        val heroe = sharedPref.getString(superHero.id.toString(), "{}")
        return heroe.let {
            gson.fromJson(it, SuperHero::class.java)
        }
    }
}