package com.pgonrod.app.data

import android.content.Context
import com.google.gson.Gson
import com.pgonrod.app.errors.Either
import com.pgonrod.app.errors.ErrorApp
import com.pgonrod.app.errors.left
import com.pgonrod.app.errors.right

class XmlExt(context: Context, title: String){
    val sharedpref = context.getSharedPreferences(title, Context.MODE_PRIVATE)
    val editor = sharedpref.edit()
    val gson = Gson()
    fun <T : Any> save(genericId: Int, generic: T): Either<ErrorApp, Boolean> {
        try {
            val jsonGeneric = gson.toJson(generic, generic::class.java)
            editor.putString(genericId.toString(),jsonGeneric)
            editor.apply()
        }catch (ex: Exception){
            return ErrorApp.DatabaseErrorApp.left()
        }
        return true.right()
    }

    inline fun <reified T : Any> getGenericById(genericId: Int): Either<ErrorApp, T?>{
        return try {
            val fromJsonGeneric = sharedpref.getString(genericId.toString(), null).let {
                gson.fromJson(it, T::class.java)
            }
            fromJsonGeneric.right()
        }catch (ex:Exception){
            ErrorApp.DatabaseErrorApp.left()
        }
    }

    fun <T : Any> saveList(model: List<T>, modelId: () -> Unit): Either<ErrorApp, List<T>>{
        return try {
            model.forEach{
                editor.putString(modelId.invoke().toString(), gson.toJson(model))
            }
            editor.apply()
            model.right()
        } catch (ex: Exception){
            ErrorApp.DatabaseErrorApp.left()
        }
    }
    inline fun <reified T : Any> getAllGeneric(): Either<ErrorApp, List<T>>{
        return try {
            val genericName: MutableList<T> = mutableListOf()
            sharedpref.all.forEach { map ->
                genericName.add(gson.fromJson(map.value as String, T::class.java))
            }
            genericName.right()
        } catch (ex: Exception){
            ErrorApp.DatabaseErrorApp.left()
        }
    }
}


