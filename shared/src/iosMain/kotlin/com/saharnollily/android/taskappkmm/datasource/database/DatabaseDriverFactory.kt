package com.saharnollily.android.taskappkmm.datasource.database

import com.saharnollily.taskapp.database.TaskDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(TaskDatabase.Schema, "task.db")
    }
}