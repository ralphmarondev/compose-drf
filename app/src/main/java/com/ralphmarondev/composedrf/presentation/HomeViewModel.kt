package com.ralphmarondev.composedrf.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.composedrf.data.RetrofitInstance
import com.ralphmarondev.composedrf.model.Person
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val people = MutableLiveData<List<Person>>()

    fun fetchPeople() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPeople()
                people.value = response
            } catch (e: Exception) {
                Log.d("FETCH", "ERROR: ${e.message}")
            }
        }
    }

    fun addPerson(name: String, age: Int, response: (Boolean, String?) -> Unit) {
        val newPerson = Person(name = name, age = age)

        viewModelScope.launch {
            try {
                val createdPerson = RetrofitInstance.api.createPerson(newPerson)
                Log.d("POST", "SUCCESS: $createdPerson")
                response(true, null)
            } catch (e: Exception) {
                Log.d("POST", "ERROR: ${e.message}")
                response(false, "${e.message}")
            }
        }
    }

    fun updatePerson(id: Int, person: Person, response: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.updatePerson(id, person)
                Log.d("PUT", "SUCCESS: $id added successfully.")
                response(true, null)
            } catch (e: Exception) {
                Log.d("PUT", "ERROR: ${e.message}")
                response(false, e.message)
            }
        }
    }
}