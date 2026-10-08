package com.example.circuitdesigner.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.circuitdesigner.model.CircuitComponent
import com.example.circuitdesigner.model.CircuitState
import com.example.circuitdesigner.model.ComponentType
import com.example.circuitdesigner.model.Connection
import com.example.circuitdesigner.model.SimulationResult
import com.example.circuitdesigner.simulation.CircuitEngine

class CircuitViewModel : ViewModel() {

    private val _components = mutableStateListOf<CircuitComponent>()
    val components: List<CircuitComponent> = _components

    private val _connections = mutableStateListOf<Connection>()
    val connections: List<Connection> = _connections

    private val engine = CircuitEngine()

    private var _result: SimulationResult = SimulationResult()
    val result: SimulationResult
        get() = _result

    fun addComponent(type: ComponentType, x: Float, y: Float) {
        val id = "${type.name}_${System.currentTimeMillis()}"

        val defaultValue = when (type) {
            ComponentType.RESISTOR -> 220.0
            ComponentType.VOLTAGE_SOURCE -> 9.0
            ComponentType.CAPACITOR -> 100.0
            ComponentType.LED -> 2.0
            ComponentType.SWITCH -> 1.0
            ComponentType.GROUND -> 0.0
            ComponentType.WIRE -> 0.0
            ComponentType.NONE -> 0.0
        }

        _components.add(
            CircuitComponent(
                id = id,
                type = type,
                value = defaultValue,
                x = x,
                y = y,
                width = 115f,
                height = 68f
            )
        )
    }

    fun connect(from: String, to: String) {
        _connections.add(
            Connection(
                fromComponentId = from,
                fromPin = "out",
                toComponentId = to,
                toPin = "in"
            )
        )
    }

    fun simulate() {
        val state = CircuitState(
            components = _components.toList(),
            connections = _connections.toList()
        )
        _result = engine.simulate(state)
    }

    fun clear() {
        _components.clear()
        _connections.clear()
        _result = SimulationResult()
    }
}
