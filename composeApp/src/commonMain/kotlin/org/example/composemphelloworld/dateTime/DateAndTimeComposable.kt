package org.example.composemphelloworld.dateTime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

data class City(val name: String, val timeZone: TimeZone)

@Composable
fun DateAndTimeComposable() {
    val cities = remember {
        listOf(City(name = "Berlin", timeZone = TimeZone.of(zoneId = "Europe/Berlin")),
            City(name = "London", timeZone = TimeZone.of(zoneId = "Europe/London")),
            City(name = "New York", timeZone = TimeZone.of(zoneId = "America/New_York")),
            City(name = "Los Angeles", timeZone = TimeZone.of(zoneId = "America/Los_Angeles")),
            City(name = "Tokyo", timeZone = TimeZone.of(zoneId = "Asia/Tokyo")),
            City(name = "Sydney", timeZone = TimeZone.of(zoneId = "Australia/Sydney")))
    }
    var cityTimes by remember {
        mutableStateOf(listOf<Pair<City, LocalDateTime>>())
    }
    LaunchedEffect(key1 = true) {
        while (true) {
            cityTimes = cities.map {
                //now represents the data and time of UTC
                val now = Clock.System.now()
                //toLocalDateTime would represents the data and time of specified time zone.
                it to now.toLocalDateTime(timeZone = it.timeZone)
            }
            delay(timeMillis = 1000L)
        }
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cityTimes) { (city, dateTime) ->
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text= city.name, fontSize = 30.sp, fontWeight = FontWeight.Bold)

                Column(horizontalAlignment = Alignment.End) {
                    Text(text = dateTime.format(
                        LocalDateTime.Format {
                            hour()
                            chars(value = ":")
                            minute()
                            chars(value = ":")
                            second()
                        }
                    ), fontSize = 30.sp, fontWeight = FontWeight.Light)

                    Text(text= dateTime.format(
                        LocalDateTime.Format {
                            dayOfMonth()
                            char(value = '/')
                            monthNumber()
                            char(value = '/')
                            year()
                        }
                    ), fontSize = 16.sp, fontWeight = FontWeight.Light)
                }
            }
        }
    }
}