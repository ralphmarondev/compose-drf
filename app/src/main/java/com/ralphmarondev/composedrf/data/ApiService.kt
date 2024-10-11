package com.ralphmarondev.composedrf.data

import com.ralphmarondev.composedrf.model.Person
import retrofit2.http.GET

interface ApiService {
    @GET("/api/persons")
    suspend fun getPeople(): List<Person>
}