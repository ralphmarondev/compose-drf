package com.ralphmarondev.composedrf.data

import com.ralphmarondev.composedrf.model.Person
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("api/persons/")
    suspend fun getPeople(): List<Person>

    @POST("api/persons/")
    suspend fun createPerson(@Body person: Person):Person

    @PUT("api/persons/{id}/")
    suspend fun updatePerson(
        @Path("id") id:Int,
        @Body person: Person
    )

    @DELETE("api/persons/{id}/")
    suspend fun deletePerson(@Path("id") id: Int)
}