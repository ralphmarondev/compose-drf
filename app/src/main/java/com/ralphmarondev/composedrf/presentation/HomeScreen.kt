package com.ralphmarondev.composedrf.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    darkTheme: Boolean,
    toggleDarkTheme: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        Log.d("FETCH", "INSIDE HOME SCREEN... FETCHING...")
        viewModel.fetchPeople()
    }

    val people by viewModel.people.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Compose DRF",
                        fontFamily = FontFamily.Monospace
                    )
                },
                actions = {
                    IconButton(onClick = { viewModel.fetchPeople() }) {
                        Icon(
                            imageVector = Icons.Outlined.Refresh,
                            contentDescription = ""
                        )
                    }
                    IconButton(onClick = toggleDarkTheme) {
                        val icon =
                            if (darkTheme) Icons.Outlined.LightMode else Icons.Outlined.DarkMode
                        Icon(
                            imageVector = icon,
                            contentDescription = "Theme Icon"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(people.size) {
                Text(
                    text = "Name: ${people[it].name}, Age: ${people[it].age}",
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}