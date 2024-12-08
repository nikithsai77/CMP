package org.example.composemphelloworld

import android.app.Application
import org.example.composemphelloworld.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            //This is used to add context to koin container
            //where ever context is req then koin will bind this app context there.
            //if we didn't pass the app context then if will store the passed context in singleton.
            androidContext(this@MyApplication)
        }
    }

}