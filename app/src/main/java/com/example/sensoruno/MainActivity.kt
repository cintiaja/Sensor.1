package com.example.sensoruno

import android.annotation.SuppressLint
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sensoruno.ui.theme.Sensor.SensorScreen
import com.example.sensoruno.ui.theme.Sensor.SensorViewModel
import com.example.sensoruno.ui.theme.SensorUnoTheme

class MainActivity : ComponentActivity() {

    // El "conector" con los sensores del celular
    private lateinit var sensorManager: SensorManager

    // Mi ViewModel (la cocina). Android lo mantiene vivo
    private val viewModel: SensorViewModel by viewModels()

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pido a Android el SensorManager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        setContent {
            // Si tu proyecto tiene un Theme, ponlo aquí envolviendo la pantalla
            SensorScreen(viewModel)
        }
    }

    // La app se ve: ENCIENDO los sensores
    override fun onResume() {
        super.onResume()

        // Busco los sensores (pueden ser null si el celular no los tiene)
        val acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)//

        // El ?.let significa: "solo si existe, regístralo"
        acelerometro?.let {
            sensorManager.registerListener(viewModel, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        giroscopio?.let {
            sensorManager.registerListener(viewModel, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    // La app sale de pantalla: APAGO los sensores para ahorrar batería
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(viewModel)
    }
}
