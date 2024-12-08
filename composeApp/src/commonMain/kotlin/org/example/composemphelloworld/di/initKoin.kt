package org.example.composemphelloworld.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(coinApplication: KoinAppDeclaration? = null) {
    startKoin {
        coinApplication?.invoke(this)
        modules(sharedModule, platformModule)
    }
}