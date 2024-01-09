package com.aymen.translatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aymen.translatorapp.feature_translator.presentation.TranslatorScreen
import com.aymen.translatorapp.feature_translator.presentation.TranslatorViewModel
import com.aymen.translatorapp.ui.theme.TranslatorAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TranslatorAppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    val viewModel: TranslatorViewModel = hiltViewModel()
                    val state = viewModel.state.collectAsState().value
                    TranslatorScreen(
                        state = state,
                        onEvent = { viewModel.onEvent(it) },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
