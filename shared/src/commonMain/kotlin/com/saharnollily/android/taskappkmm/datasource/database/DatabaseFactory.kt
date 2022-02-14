package com.saharnollily.android.taskappkmm.datasource.database

import com.saharnollily.taskapp.database.TaskDatabase

class DatabaseFactory(private val databaseDriverFactory: DatabaseDriverFactory) {
    fun createDatabase(): TaskDatabase {
        return TaskDatabase(databaseDriverFactory.createDriver())
    }
}