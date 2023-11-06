package com.pgonrod.superheroes.data.biography.api

import com.google.gson.annotations.SerializedName

data class BiographyApiModel(@SerializedName("fullName") val fullName: String)
