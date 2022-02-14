package com.saharnollily.android.taskappkmm.di

import com.saharnollily.android.taskappkmm.datasource.database.DatabaseDriverFactory
import com.saharnollily.android.taskappkmm.datasource.database.DatabaseFactory
import com.saharnollily.android.taskappkmm.datasource.database.task.TaskDao
import com.saharnollily.android.taskappkmm.datasource.database.task.TaskDaoImp
import com.saharnollily.taskapp.database.TaskDatabase

class DatabaseModule {

    private val databaseDriverFactory: DatabaseDriverFactory by lazy { DatabaseDriverFactory() }
    val taskDatabase: TaskDatabase by lazy {
        DatabaseFactory(databaseDriverFactory = databaseDriverFactory).createDatabase()
    }
    val taskDao: TaskDao by lazy {
        TaskDaoImp(
            taskDatabase = taskDatabase
        )
    }


}


