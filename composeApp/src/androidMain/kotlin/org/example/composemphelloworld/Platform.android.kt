package org.example.composemphelloworld

import android.os.Build

actual fun getPlatform(): Platform = object: Platform {
    override val name: String get() = "Android ${Build.VERSION.SDK_INT}"
}