package com.jammes.pressaook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jammes.pressaook.ui.theme.PressaoOKTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PressaoOKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val pas = remember { mutableStateOf("") }
    val pad = remember { mutableStateOf("") }
    val resultado = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pressão Arterial",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = pas.value,
            onValueChange = { pas.value = it },
            label = { Text("PAS (mmHg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = pad.value,
            onValueChange = { pad.value = it },
            label = { Text("PAD (mmHg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            resultado.value = when (pas.value.toInt()) {
                in Int.MIN_VALUE until 120 -> {
                    when (pad.value.toInt()) {
                        in Int.MIN_VALUE until 80 -> "Normal"
                        in 80..84 -> "Elevada"
                        else -> "Hipertensão estágio 1"
                    }
                }
                in 120..129 -> {
                    when (pad.value.toInt()) {
                        in Int.MIN_VALUE until 80 -> "Normal"
                        in 80..84 -> "Elevada"
                        else -> "Hipertensão estágio 1"
                    }
                }
                in 130..139 -> {
                    when (pad.value.toInt()) {
                        in Int.MIN_VALUE until 80 -> "Hipertensão estágio 1"
                        in 80..84 -> "Hipertensão estágio 1"
                        else -> "Hipertensão estágio 2"
                    }
                }
                in 140..159 -> {
                    when (pad.value.toInt()) {
                        in Int.MIN_VALUE until 80 -> "Hipertensão estágio 1"
                        in 80..89 -> "Hipertensão estágio 2"
                        else -> "Hipertensão grave"
                    }
                }
                else -> {
                    when (pad.value.toInt()) {
                        in Int.MIN_VALUE until 80 -> "Hipertensão estágio 1"
                        in 80..89 -> "Hipertensão grave"
                        else -> "Hipertensão grave"
                    }
                }
            }
        }) {
            Text(text = "Calcular")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Resultado: ${resultado.value}",
            fontSize = 18.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PressaoOKTheme {
        App()
    }
}