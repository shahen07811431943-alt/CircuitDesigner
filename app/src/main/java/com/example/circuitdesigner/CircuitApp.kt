package com.example.circuitdesigner

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.circuitdesigner.ui.screens.CircuitScreen
import com.example.circuitdesigner.viewmodel.CircuitViewModel

@Composable
fun CircuitApp() {
    val viewModel: CircuitViewModel = viewModel()
    CircuitScreen(viewModel = viewModel)
}
