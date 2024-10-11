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
}