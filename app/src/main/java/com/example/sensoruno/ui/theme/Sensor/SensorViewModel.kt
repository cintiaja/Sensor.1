package com.example.sensoruno.ui.theme.Sensor

import android.hardware.SensorEventListener
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import android.hardware.Sensor
import android.hardware.SensorEvent

class SensorViewModel: ViewModel(), SensorEventListener {//aqui le pedimos que escuche
    var acX by mutableStateOf(0.0f)

    var acY by mutableStateOf(0.0f)
    var acZ by mutableStateOf(0.0f)//glaih.o4h


    //variables giroscopio
    var giX by mutableStateOf(0.0f)
    var giY by mutableStateOf(0.0f)
    var giZ by mutableStateOf(0.0f)//esta es la variable que van a tener los valores y luego se lo van a pasar a
    //SensorScreen para que lo muestre
    var ultimaLecturaAcc = 0L
    var ultimaLecturaGiro = 0L
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event == null) return
        val ahora = System.currentTimeMillis()//1000 milisegundos 0 1 segundo, asi que 250ms = un cuarto de segundo

        when (event.sensor.type){
            Sensor.TYPE_ACCELEROMETER -> {
                if (ahora - ultimaLecturaAcc >= 200){//filtro hace que cada 200 milisegundos mande un dato, si no lo ignora
                    ultimaLecturaAcc = ahora//pasa el valor y toma lugar
                    acX = event.values[0]
                    acY = event.values[1]
                    acZ = event.values[2]//du
                }
            }

            Sensor.TYPE_GYROSCOPE -> {//
                if (ahora - ultimaLecturaGiro >= 200){
                    ultimaLecturaGiro = ahora
                    giX = event.values[0]
                    giY =event.values[1]
                    giZ = event.values[2]
                }

            }
        }
    }
    //mutable: que se puede cambiar, State: estado, el valor que tiene ahorita,of(0): empieza con valor 0
    //
}