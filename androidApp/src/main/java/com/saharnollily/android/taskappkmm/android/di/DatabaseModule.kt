package com.saharnollily.android.taskappkmm.android.di

import android.content.Context
import com.saharnollily.android.taskappkmm.datasource.database.DatabaseDriverFactory
import com.saharnollily.android.taskappkmm.datasource.database.DatabaseFactory
import com.saharnollily.android.taskappkmm.datasource.database.task.TaskDao
import com.saharnollily.android.taskappkmm.datasource.database.task.TaskDaoImp
import com.saharnollily.taskapp.database.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase{
        return DatabaseFactory(DatabaseDriverFactory(context)).createDatabase()
    }


    @Singleton
    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDao{
        return TaskDaoImp(taskDatabase = taskDatabase)
    }
}