package com.example.circuitdesigner.model

import androidx.compose.ui.geometry.Offset
import kotlin.math.abs

enum class ComponentType {
    RESISTOR,
    VOLTAGE_SOURCE,
    GROUND,
    LED,
    CAPACITOR,
    SWITCH,
    WIRE,
    NONE
}

data class CircuitComponent(
    val id: String,
    val type: ComponentType,
    val value: Double = 1.0,
    val x: Float,
    val y: Float,
    val width: Float = 120f,
    val height: Float = 70f,
    val rotation: Float = 0f
) {
    fun center(): Offset = Offset(x + width / 2f, y + height / 2f)

    fun contains(point: Offset): Boolean {
        return point.x in x..(x + width) && point.y in y..(y + height)
    }

    fun distanceTo(point: Offset): Float {
        val c = center()
        return abs(c.x - point.x) + abs(c.y - point.y)
    }
}

data class Connection(
    val fromComponentId: String,
    val fromPin: String,
    val toComponentId: String,
    val toPin: String
)

data class CircuitState(
    val components: List<CircuitComponent> = emptyList(),
    val connections: List<Connection> = emptyList()
)

data class SimulationResult(
    val voltage: Double = 0.0,
    val current: Double = 0.0,
    val status: String = "Idle",
    val logs: List<String> = emptyList()
)

fun CircuitComponent.label(): String = when (type) {
    ComponentType.RESISTOR -> "R"
    ComponentType.VOLTAGE_SOURCE -> "V"
    ComponentType.GROUND -> "GND"
    ComponentType.LED -> "LED"
    ComponentType.CAPACITOR -> "C"
    ComponentType.SWITCH -> "SW"
    ComponentType.WIRE -> "WIRE"
    ComponentType.NONE -> ""
}
