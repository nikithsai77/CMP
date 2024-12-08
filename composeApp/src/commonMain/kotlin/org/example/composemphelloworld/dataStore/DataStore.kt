package org.example.composemphelloworld.dataStore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun DataStore(dataStore: DataStore<Preferences>) {
    val counter by dataStore.data
        .map {
            val key = intPreferencesKey(name = "counter")
            it[key] ?: 0
        }
        .collectAsStateWithLifecycle(initialValue = 0)
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = counter.toString(), textAlign = TextAlign.Center, fontSize = 50.sp)
        
        Button(onClick = {
            scope.launch {
                dataStore.edit {
                    val key = intPreferencesKey(name = "counter")
                    it[key] = counter + 1
                }
            }
        }) {
            Text(text = "Increment")
        }
    }
}