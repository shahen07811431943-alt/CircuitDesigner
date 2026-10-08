package com.example.circuitdesigner.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.circuitdesigner.model.ComponentType
import com.example.circuitdesigner.ui.components.CircuitCanvas
import com.example.circuitdesigner.ui.components.ComponentPalette
import com.example.circuitdesigner.viewmodel.CircuitViewModel

@Composable
fun CircuitScreen(viewModel: CircuitViewModel) {
    var selectedType by remember { mutableStateOf<ComponentType?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { viewModel.simulate() }) {
                Text("محاكاة")
            }
            Button(onClick = { viewModel.clear() }) {
                Text("مسح")
            }
        }

        Row(modifier = Modifier.fillMaxSize()) {
            ComponentPalette { type ->
                selectedType = type
            }

            CircuitCanvas(
                components = viewModel.components,
                onTap = { offset ->
                    selectedType?.let { type ->
                        viewModel.addComponent(type, offset.x, offset.y)
                        selectedType = null
                    }
                }
            )
        }

        if (viewModel.result.logs.isNotEmpty() || viewModel.result.status != "Idle") {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("الحالة: ${viewModel.result.status}")
                Text("الجهد: ${String.format("%.2f", viewModel.result.voltage)}V")
                Text("التيار: ${String.format("%.4f", viewModel.result.current)}A")
                viewModel.result.logs.forEach { log ->
                    Text("- $log")
                }
            }
        }
    }
}
