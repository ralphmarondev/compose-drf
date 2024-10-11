package com.ralphmarondev.composedrf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ralphmarondev.composedrf.presentation.HomeScreen
import com.ralphmarondev.composedrf.ui.theme.ComposeDRFTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var darkTheme by remember { mutableStateOf(false) }

            ComposeDRFTheme(darkTheme = darkTheme) {
                HomeScreen(
                    darkTheme = darkTheme,
                    toggleDarkTheme = { darkTheme = !darkTheme }
                )
            }
        }
    }
}