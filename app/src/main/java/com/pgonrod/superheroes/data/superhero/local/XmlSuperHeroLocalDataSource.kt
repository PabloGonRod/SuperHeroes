package com.pgonrod.superheroes.data.superhero.local

import android.content.Context
import com.pgonrod.app.data.XmlExt
import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.superheroes.domain.Powerstats
import com.pgonrod.superheroes.domain.SuperHero

class XmlSuperHeroLocalDataSource(val generic: XmlExt) : SuperHeroLocalDataSource {


    override fun saveSuperHeroes(superHero: List<SuperHero>): Either<ErrorApp, List<SuperHero>> {

        return generic.saveList(superHero) { superHero.forEach { it.id } }
    }

    override fun getAllSuperHeroes(): Either<ErrorApp, List<SuperHero>> {

        return generic.getAllGeneric()
    }

    override fun getSuperhero(superHero: SuperHero): SuperHero? {
        TODO("Not yet implemented")
    }
    /*fun saveSuperHeroes(superHero: List<SuperHero>): Either<ErrorApp, List<SuperHero>> {
        return try {
            superHero.forEach{
                editor.putString(it.id.toString(), gson.toJson(superHero))
            }
            editor.apply()
            superHero.right()
        } catch (ex: Exception){
            ErrorApp.DatabaseErrorApp.left()
        }
    }*/

   /* fun getAllSuperHeroes(): Either<ErrorApp, List<SuperHero>>{
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
    }*/
}