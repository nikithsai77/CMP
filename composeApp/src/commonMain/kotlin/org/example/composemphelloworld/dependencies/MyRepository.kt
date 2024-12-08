package org.example.composemphelloworld.dependencies

interface MyRepository {
    fun getPlatformName(): String
}

class MyRepositoryImpl(private val dbClient: DbClient) : MyRepository {
    override fun getPlatformName(): String {
        return dbClient.getPlatformName()
    }
}
