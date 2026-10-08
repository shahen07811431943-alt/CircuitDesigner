package com.example.circuitdesigner.simulation

import com.example.circuitdesigner.model.CircuitState
import com.example.circuitdesigner.model.ComponentType
import com.example.circuitdesigner.model.SimulationResult

class CircuitEngine {
    fun simulate(state: CircuitState): SimulationResult {
        val logs = mutableListOf<String>()

        if (state.components.isEmpty()) {
            return SimulationResult(
                voltage = 0.0,
                current = 0.0,
                status = "No circuit",
                logs = listOf("لا توجد مكونات في اللوحة")
            )
        }

        var voltageSum = 0.0
        var totalResistance = 0.0
        var sourceCount = 0

        state.components.forEach { component ->
            when (component.type) {
                ComponentType.VOLTAGE_SOURCE -> {
                    voltageSum += component.value
                    sourceCount += 1
                    logs.add("مصدر جهد: ${component.value}V")
                }
                ComponentType.RESISTOR -> {
                    totalResistance += component.value
                    logs.add("مقاومة: ${component.value}Ω")
                }
                ComponentType.GROUND -> {
                    logs.add("توصيل أرضي")
                }
                ComponentType.LED -> {
                    logs.add("ديود LED: قيمة التشغيل ${component.value}V")
                }
                ComponentType.CAPACITOR -> {
                    logs.add("مكثف: ${component.value}uF")
                }
                ComponentType.SWITCH -> {
                    logs.add("مفتاح موجود")
                }
                ComponentType.WIRE -> {
                    logs.add("سلك متصل")
                }
                ComponentType.NONE -> Unit
            }
        }

        val averageVoltage = if (sourceCount > 0) voltageSum / sourceCount else 0.0
        val current = if (totalResistance > 0.0) averageVoltage / totalResistance else 0.05

        val status = if (sourceCount > 0) {
            if (averageVoltage > 0.0) "Running" else "Idle"
        } else {
            "Idle"
        }

        return SimulationResult(
            voltage = averageVoltage,
            current = current,
            status = status,
            logs = logs.ifEmpty { listOf("تمت محاكاة الدائرة بنجاح") }
        )
    }
}
