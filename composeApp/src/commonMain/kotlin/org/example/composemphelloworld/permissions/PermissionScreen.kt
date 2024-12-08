package org.example.composemphelloworld.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory

@Composable
fun PermissionScreen() {
    val factory = rememberPermissionsControllerFactory()
    val controller = remember {
        factory.createPermissionsController()
    }
    BindEffect(controller)
    val permissionStateViewModel = viewModel {
        MyViewModelPermission(controller)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when(permissionStateViewModel.state) {
            PermissionState.Granted -> Text(text = "Record Audio Permission Granted!")
            PermissionState.DeniedAlways -> {
                Column(modifier = Modifier.fillMaxWidth().background(Color.Green), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Permission was Permanently Declined")
                    Button(onClick = {
                        controller.openAppSettings()
                    }) {
                        Text(text = "Open App Settings")
                    }
                }
            }
            else -> {
                Button(onClick = {
                    permissionStateViewModel.requestRecordAudioPermission()
                })
                {
                    Text(text = "Request Permission")
                }
            }
        }
    }
}