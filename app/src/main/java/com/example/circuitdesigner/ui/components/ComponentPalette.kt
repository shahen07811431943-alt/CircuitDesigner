package com.example.circuitdesigner.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.circuitdesigner.model.ComponentType

@Composable
fun ComponentPalette(
    onAdd: (ComponentType) -> Unit
) {
    Column(
        modifier = Modifier
            .width(180.dp)
            .fillMaxHeight()
            .background(Color(0xFF1E1E1E))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PaletteButton("مقاومة") { onAdd(ComponentType.RESISTOR) }
        PaletteButton("مصدر جهد") { onAdd(ComponentType.VOLTAGE_SOURCE) }
        PaletteButton("مكثف") { onAdd(ComponentType.CAPACITOR) }
        PaletteButton("LED") { onAdd(ComponentType.LED) }
        PaletteButton("مفتاح") { onAdd(ComponentType.SWITCH) }
        PaletteButton("أرض") { onAdd(ComponentType.GROUND) }
        PaletteButton("سلك") { onAdd(ComponentType.WIRE) }
    }
}

@Composable
private fun PaletteButton(
    label: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(label)
    }
}
