package org.example.composemphelloworld

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.example.composemphelloworld.dataStore.createDataStore
import org.example.composemphelloworld.dataStore.DATA_STORE_FILE_NAME

fun createDataStoreIos() : DataStore<Preferences> {
    return createDataStore {
        val directory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        directory.path + "/$DATA_STORE_FILE_NAME"
    }
}