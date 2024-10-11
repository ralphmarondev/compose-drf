package com.ralphmarondev.composedrf.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ralphmarondev.composedrf.model.Person
import com.ralphmarondev.composedrf.presentation.components.NewPersonDialog
import com.ralphmarondev.composedrf.presentation.components.UpdatePersonDialog

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
    var personToUpdate by remember {
        mutableStateOf(
            Person(
                name = "",
                age = 0
            )
        )
    }
    var showNewPersonDialog by remember { mutableStateOf(false) }
    var showUpdatePersonDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showNewPersonDialog = !showNewPersonDialog }) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "New Person"
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(people.size) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Column(modifier = Modifier.padding(4.dp)) {
                            Text(
                                text = people[it].name,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.W500,
                                fontSize = 18.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = people[it].age.toString(),
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.W400,
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }

                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = "Delete",
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                            IconButton(
                                onClick = {
                                    personToUpdate = personToUpdate.copy(
                                        id = people[it].id,
                                        name = people[it].name,
                                        age = people[it].age
                                    )
                                    showUpdatePersonDialog = !showUpdatePersonDialog
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = "Edit",
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Outlined.Info,
                                    contentDescription = "View",
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showNewPersonDialog) {
            NewPersonDialog(
                onDismiss = { showNewPersonDialog = !showNewPersonDialog },
                onSave = { name, age ->
                    viewModel.addPerson(
                        name = name,
                        age = age,
                        response = { isSuccess, msg ->
                            if (isSuccess) {
                                showNewPersonDialog = !showNewPersonDialog
                            } else {
                                Toast.makeText(context, "Failed: $msg", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            )
        }

        if (showUpdatePersonDialog) {
            UpdatePersonDialog(
                onDismiss = { showUpdatePersonDialog = !showUpdatePersonDialog },
                person = personToUpdate,
                onUpdate = { id, name, age ->
                    viewModel.updatePerson(
                        id = id,
                        person = Person(name = name, age = age),
                        response = { isSuccess, msg ->
                            if (isSuccess) {
                                showUpdatePersonDialog = !showUpdatePersonDialog
                                Toast.makeText(context, "Updated successfully!", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                Toast.makeText(context, "Error: $msg", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            )
        }
    }
}