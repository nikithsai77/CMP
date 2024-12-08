package org.example.composemphelloworld.permissions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import kotlinx.coroutines.launch

class MyViewModelPermission(private val controller: PermissionsController): ViewModel() {

    var state by mutableStateOf(PermissionState.NotDetermined)
        private set

    init {
        viewModelScope.launch {
            state = controller.getPermissionState(Permission.RECORD_AUDIO)
        }
    }

    fun requestRecordAudioPermission() = viewModelScope.launch {
        try {
            controller.providePermission(Permission.RECORD_AUDIO)
            state = PermissionState.Granted
        } catch (e: DeniedAlwaysException) {
            //If user denied the req permission with tick mark of checkBox(Never Show Again).
            state = PermissionState.DeniedAlways
        } catch (e: DeniedException) {
            //If user denied the req permission.
            state = PermissionState.Denied
        } catch (e: RequestCanceledException) {
            //If user cancel req permission without response (only on android).
            e.printStackTrace()
        }
    }

}