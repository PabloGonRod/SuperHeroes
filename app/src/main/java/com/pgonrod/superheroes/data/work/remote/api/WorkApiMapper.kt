package com.pgonrod.superheroes.data.work.remote.api

import com.pgonrod.superheroes.domain.Work

fun WorkApiModel.toDomain(): Work = Work(this.occupation)