package org.example.composemphelloworld.di

import org.koin.core.module.Module
import org.example.composemphelloworld.dependencies.DbClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    singleOf(::DbClient)
}