package com.example.circuitdesigner.ui.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import com.example.circuitdesigner.model.CircuitComponent
import com.example.circuitdesigner.model.ComponentType
import com.example.circuitdesigner.model.label

@Composable
fun CircuitCanvas(
    components: List<CircuitComponent>,
    onTap: (Offset) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    onTap(offset)
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            components.forEach { component ->
                val color = when (component.type) {
                    ComponentType.RESISTOR -> Color(0xFF4FC3F7)
                    ComponentType.VOLTAGE_SOURCE -> Color(0xFF81C784)
                    ComponentType.GROUND -> Color(0xFFEF5350)
                    ComponentType.LED -> Color(0xFFFFEB3B)
                    ComponentType.CAPACITOR -> Color(0xFFBA68C8)
                    ComponentType.SWITCH -> Color(0xFFB0BEC5)
                    ComponentType.WIRE -> Color(0xFF90CAF9)
                    ComponentType.NONE -> Color.White
                }

                drawRect(
                    color = color,
                    topLeft = Offset(component.x, component.y),
                    size = Size(component.width, component.height),
                    style = Stroke(width = 3f)
                )

                drawContext.canvas.nativeCanvas.drawText(
                    component.label(),
                    component.x + component.width / 2f,
                    component.y + component.height / 2f,
                    Paint().apply {
                        color = android.graphics.Color.WHITE
                        textSize = 28f
                        textAlign = Paint.Align.CENTER
                    }
                )
            }
        }
    }
}
