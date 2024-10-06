package com.example.kiemtragk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiemtragk.ui.theme.KiemTraGKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KiemTraGKTheme {
                TemperatureInputScreen()
            }
        }
    }
}

@Composable
fun TemperatureInputScreen() {
    // Trạng thái để lưu giá trị nhiệt độ nhập vào
    var temperatureInput by remember { mutableStateOf(TextFieldValue("")) }

    // Trạng thái để lưu giá trị nhiệt độ chuyển đổi
    val temperature = temperatureInput.text.toFloatOrNull()

    // Tính toán màu sắc và nhãn dựa trên nhiệt độ
    val (circleColor, weatherText) = when {
        temperature == null -> Color.Gray to "Nhập nhiệt độ"
        temperature < 25 -> Color.Blue to "Thời tiết lạnh"
        temperature in 25.0..28.0 -> Color.Green to "Thời tiết ôn hòa"
        temperature in 29.0..35.0 -> Color.Yellow to "Thời tiết nóng"
        temperature > 35 -> Color.Red to "Thời tiết rất nóng"
        else -> Color.Gray to "Không xác định"
    }

    // Giao diện người dùng với cải tiến
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tiêu đề
        Text(
            text = "Nhập nhiệt độ (°C)",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Ô nhập liệu nhiệt độ
        OutlinedTextField(
            value = temperatureInput,
            onValueChange = { temperatureInput = it },
            label = { Text("Nhiệt độ") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Hình tròn thay đổi màu sắc
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(color = circleColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = weatherText,
                color = Color.White,
                fontSize = 18.sp,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Chú thích hướng dẫn nhập liệu
        Text(
            text = "Nhập nhiệt độ và xem loại thời tiết",
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TemperatureInputScreenPreview() {
    KiemTraGKTheme {
        TemperatureInputScreen()
    }
}
