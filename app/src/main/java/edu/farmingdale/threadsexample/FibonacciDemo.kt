package edu.farmingdale.threadsexample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

@Composable
fun FibonacciDemoNoBgThrd() {
    var answer by remember { mutableStateOf("") }
    var textInput by remember { mutableStateOf("40") }

    Column {
        Spacer(modifier = Modifier.padding(60.dp))
        Row {
            TextField(
                value = textInput,
                onValueChange = { textInput = it },
                label = { Text("Number?") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Button(onClick = {
                val num = textInput.toLongOrNull() ?: 0
                val fibNumber = fibonacci(num)
                answer = NumberFormat.getNumberInstance(Locale.US).format(fibNumber)
            }) {
                Text("Fibonacci")
            }
        }

        Text("Result: $answer")
    }
}

fun fibonacci(n: Long): Long {
    return if (n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)
}

@Composable
fun FibonacciDemoWithCoroutine() {
    var answer by remember { mutableStateOf("") }
    var textInput by remember { mutableStateOf("40") }
    val coroutineScope = rememberCoroutineScope()

    Column {
        Spacer(modifier = Modifier.padding(60.dp))
        Row {
            TextField(
                value = textInput,
                onValueChange = { textInput = it },
                label = { Text("Number?") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Button(onClick = {
               coroutineScope.launch(Dispatchers.Default) {
                    val num = textInput.toLongOrNull() ?: 0
                    val fibNumber = fibonacci(num)
                    answer = NumberFormat.getNumberInstance(Locale.US).format(fibNumber)
                }
            }) {
                Text("Fibonacci")
            }
        }

        Text("Result: $answer")
    }
}
