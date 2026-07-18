package com.otakcanary.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.otakcanary.weather.domain.Day
import com.otakcanary.weather.ui.WeatherViewModel
import org.jetbrains.compose.resources.painterResource

import weatherapp.sharedui.generated.resources.Res
import weatherapp.sharedui.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    val viewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory)
    val state by remember { viewModel.state }
    MaterialTheme {
        Scaffold(
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (state.isLoading) {
                    Text("Loading...")
                } else {
                    LazyRow {
                        items(state.weather) { day: Day ->
                            WeatherDay(day, Modifier)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherDay(
    day: Day,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 12.dp,
                bottom = 12.dp
            ) // TODO they must be distributed with space between them
            .size(width = 64.dp, height = 107.dp) // TODO height should calculate automatically
            .clip(CircleShape)

            .background(color = MaterialTheme.colorScheme.onSurface)
    ) {
        Text(
            text = day.date,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = day.temperature.toString(),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
fun WeatherDayPreview() {
    WeatherDay(Day("2025", 25.3), Modifier)
}