@file:OptIn(KoinExperimentalAPI::class)

package org.example.composemphelloworld

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.compose_multiplatform123
import kmp.composeapp.generated.resources.sample
import kotlinx.coroutines.launch
import org.example.composemphelloworld.battery.BatteryComposable
import org.example.composemphelloworld.battery.BatteryManager
import org.example.composemphelloworld.dataStore.DataStore
import org.example.composemphelloworld.dateTime.DateAndTimeComposable
import org.example.composemphelloworld.dependencies.MyViewModel
import org.example.composemphelloworld.networking.InitiateHttpClient
import org.example.composemphelloworld.networking.util.onError
import org.example.composemphelloworld.networking.util.onSuccess
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
@Preview
fun App(batteryManager: BatteryManager, httpClient: InitiateHttpClient, dataStore: DataStore<Preferences>) {
    MaterialTheme {
       Scaffold(topBar = { topAppBar() }) {
           val viewModel = koinViewModel<MyViewModel>()
           var finalText by remember {
               mutableStateOf(value = "")
           }
           var enterText by remember {
               mutableStateOf(value = "")
           }
           val scope = rememberCoroutineScope()

//           BatteryComposable(batteryManager)
//           PermissionScreen()
//           DateAndTimeComposable()
//           DataStore(dataStore = dataStore)

           Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
               Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                   Image(painter = painterResource(Res.drawable.compose_multiplatform123), contentDescription = null)
                   Spacer(modifier = Modifier.height(8.dp))

                   Text(text = stringResource(Res.string.sample))
                   Spacer(modifier = Modifier.height(8.dp))

                   Text(text = viewModel.getPlatformName())
                   Spacer(modifier = Modifier.height(8.dp))

                   TextField(value = enterText, onValueChange = {
                       enterText = it
                   }, placeholder = {
                       Text(text = "Enter Your Data Here...")
                   }, modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth())
                   if (finalText.isNotBlank()) Text(text = finalText)
                   Button(onClick = {
                       scope.launch {
                           httpClient.getData(enterText)
                               .onSuccess {
                                   finalText = it
                               }
                               .onError {
                                   finalText = it.name
                               }
                       }
                   }) {
                       Text(text = "Click Here")
                   }
               }
           }
       }
    }
}

@Composable
fun topAppBar() {
    Row(modifier = Modifier.fillMaxWidth().wrapContentHeight().background(MaterialTheme.colors.onPrimary).padding(16.dp)) {
        Text(text = "KMP", color = MaterialTheme.colors.primary, fontSize = 18.sp)
    }
}