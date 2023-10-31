package com.pgonrod.superheroes.data.work.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.app.errors.left
import com.pgonrod.app.errors.right
import com.pgonrod.superheroes.domain.SuperHero
import com.pgonrod.superheroes.domain.Work

class XmlWorkLocalDataSource(val sharedPref: SharedPreferences) {

    private val editor = sharedPref.edit()
    private val gson = Gson()

    fun saveWork(heroId:Int,work: Work): Either<ErrorApp, Boolean> {
        try {
            val jsonWork = gson.toJson(work, Work::class.java)
            editor.putString(heroId.toString(),jsonWork)
            editor.apply()
        }catch (ex: Exception){
            return ErrorApp.DatabaseErrorApp.left()
        }
        return true.right()
    }

    fun getWork(heroId: Int): Either<ErrorApp, Work> {
        try {
            val work = sharedPref.getString(heroId.toString(), null).let {
                gson.fromJson(it, Work::class.java)
            }
            return work.right()

        }catch (ex:Exception){
            return ErrorApp.DatabaseErrorApp.left()
        }
    }

}