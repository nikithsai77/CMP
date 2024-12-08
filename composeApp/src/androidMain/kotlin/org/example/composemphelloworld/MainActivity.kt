package org.example.composemphelloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.composemphelloworld.battery.BatteryManager
import org.example.composemphelloworld.dataStore.createDataStore
import org.example.composemphelloworld.networking.InitiateHttpClient
import org.example.composemphelloworld.networking.createHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isChecked = true
        lifecycleScope.launch {
            delay(timeMillis = 4000)
            isChecked = false
        }
        //without this, we will be see the default system behaviour of splash screen and dismiss automatically based on the app's launch time.
        //by using this we can handle the splash screen programmatically.
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                //this block is not one time call, this block calls periodically
                //so this is right place to operation and based upon that result we
                //write the logic to move another screen & by this way we can control over the splash screen's duration.
                isChecked
            }
        }
        setContent {
            App(dataStore = remember {
                createDataStore(application)
            },
            batteryManager = remember { BatteryManager(application) }, httpClient = InitiateHttpClient(createHttpClient(OkHttp.create())))
        }
    }
}