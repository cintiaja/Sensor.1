package com.example.sensoruno.ui.theme.Sensor

import android.R.attr.x
import android.R.attr.y
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sensoruno.ui.theme.SensorUnoTheme

@Composable
fun SensorScreen(viewModel: SensorViewModel){//aqui pones el viewModel: como llames el archivo
    //variables de aceleroscopio
    var ax = 0.0f
    var ay = 0.0f
    var az = 0.0f

    //variables de giroscopio
    var gx = 0.0f
    var gy = 0.0f
    var gz = 0.0f
    Column(
        modifier = Modifier.padding(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("RUN TRACK!!!")
        Spacer(modifier = Modifier.width(60.dp))
        Text("Acelerometro")
        Text(text = "X: ${"%.2f".format(viewModel.acX)}")//muestra los valores con solo 2 decimales
        Text(text = "Y: ${"%.2f".format(viewModel.acY)}")
        Text(text = "Z: ${"%.2f".format(viewModel.acZ)}")
        Spacer(modifier = Modifier.width(30.dp))
        Text("Lectura recibida: ${viewModel.ultimaLecturaAcc}")

        Spacer(modifier = Modifier.width(40.dp))
        Text("Giroscopio")
        Text(text = "X: ${"%.2f".format(viewModel.giX)}")
        Text(text = "Y: ${"%.2f".format(viewModel.giY)}")
        Text(text = "Z: ${"%.2f".format(viewModel.giZ)}")
        Spacer(modifier = Modifier.width(40.dp))
        Text("Lectura recibida: ${viewModel.ultimaLecturaGiro}")//pondremos la variable despues del $ view.variable entre llaves tod

    }
}
@Preview(showBackground = true)
@Composable
fun SensorScreenPreview(){
    SensorUnoTheme() {
        SensorScreen(SensorViewModel())
    }
}
// NOTA : siempre que haya uun problema y modificues ajustes por algo salte y vuelve a meterte