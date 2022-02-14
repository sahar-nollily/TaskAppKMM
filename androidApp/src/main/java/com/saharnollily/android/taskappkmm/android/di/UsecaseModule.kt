package com.saharnollily.android.taskappkmm.android.di

import com.saharnollily.android.taskappkmm.datasource.database.task.TaskDao
import com.saharnollily.android.taskappkmm.domain.usecase.AddTask
import com.saharnollily.android.taskappkmm.domain.usecase.GetTasks
import com.saharnollily.taskapp.database.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {

    @Singleton
    @Provides
    fun provideGetTasks(taskDao: TaskDao): GetTasks{
        return GetTasks(taskDao = taskDao)
    }


    @Singleton
    @Provides
    fun provideAddTask(taskDao: TaskDao): AddTask{
        return AddTask(taskDao = taskDao)
    }
}