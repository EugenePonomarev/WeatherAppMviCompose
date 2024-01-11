package com.crazycats.weatherappmvicompose.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crazycats.weatherappmvicompose.R
import com.crazycats.weatherappmvicompose.presentation.model.WeatherState
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {

    state.weatherInfo?.currentWeatherData?.let { weatherData ->
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "City", color = Color.White)
                    Text(
                        text = "Today ${
                            weatherData.time.format(
                                DateTimeFormatter.ofPattern("HH:mm")
                            )
                        }", color = Color.White
                    )
                }

                Spacer(modifier = Modifier.heightIn(16.dp))
                Image(
                    painter = painterResource(id = weatherData.weatherType.iconRes),
                    contentDescription = "weather Icon",
                    modifier = Modifier.width(200.dp)
                )
                Spacer(modifier = Modifier.heightIn(16.dp))

                Text(
                    text = "${weatherData.temperatureCelsius}°C",
                    fontSize = 50.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.heightIn(16.dp))

                Text(
                    text = weatherData.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.heightIn(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherDataDisplay(
                        value = weatherData.pressure.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(R.drawable.ic_pressure),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = weatherData.humidity.roundToInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(R.drawable.ic_drop),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = weatherData.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(R.drawable.ic_wind),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                }

            }
        }
    }
}