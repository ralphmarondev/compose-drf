package com.ralphmarondev.composedrf.data

import com.ralphmarondev.composedrf.model.Person
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/persons/")
    suspend fun getPeople(): List<Person>

    @POST("api/persons/")
    suspend fun createPerson(@Body person: Person):Person
}