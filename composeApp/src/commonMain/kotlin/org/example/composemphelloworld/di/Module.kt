package org.example.composemphelloworld.di

import org.example.composemphelloworld.dependencies.MyRepository
import org.example.composemphelloworld.dependencies.MyRepositoryImpl
import org.example.composemphelloworld.dependencies.MyViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule : Module

val sharedModule = module {
    singleOf(::MyRepositoryImpl).bind<MyRepository>()
    viewModelOf(::MyViewModel)
}