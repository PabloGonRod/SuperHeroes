package com.pgonrod.app.extensions

import com.pgonrod.superheroes.databinding.ViewErrorBinding

fun ViewErrorBinding.errorInternet(){
    val internet = "ERROR DE RED"
    this.apply {
        imageError.loadurl("https://phantom-elmundo.unidadeditorial.es/32add9264d0be97dbc167e42b2621b09/resize/473/f/webp/assets/multimedia/imagenes/2019/09/20/15689929413838.jpg")
        textError.text = internet
    }
}
fun ViewErrorBinding.errorDatabase(){
    val database = "ERROR EN LA BASE DE DATOS"
    this.apply {
        imageError.loadurl("https://fixmysite.com/site/wp-content/uploads/2019/04/database-error.jpg")
        textError.text = database
    }
}
fun ViewErrorBinding.errorUnknown(){
    val unknown = "ERROR DESCONOCIDO"
    this.apply {
        imageError.loadurl("https://ayudawp.com/wp-content/uploads/2012/03/aviso.png")
        textError.text = unknown
    }
}
