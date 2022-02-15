//
//  SharedViewModel.swift
//  iosApp
//
//  Created by Sa7ar Nollily on 10/07/1443 AH.
//  Copyright © 1443 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

class SharedViewModel: ObservableObject{
    
    private let databaseModule = DatabaseModule()
    private var usecaseModule : UsecaseModule
    private let addTask: AddTask
    private let getTask: GetTasks
    
    @Published var taskListState = TaskListState()
    @Published var addTaskState = AddTaskState()
    
    init(){
        self.usecaseModule = UsecaseModule(taskDao: databaseModule.taskDao)
        self.addTask = self.usecaseModule.addTask
        self.getTask = self.usecaseModule.getTasks
        getTasks()
    }
    
    
    func addTask(task: Task){
        self.addTask.execute(task: task).collectFlow(
            coroutineScope: nil,
            callback: { dataState in
                switch dataState != nil {
                case dataState?.isLoading:
                    self.addTaskState = AddTaskState(isLoading: dataState?.isLoading ?? false,
                                                  error: "",
                                                  data: false)
                case dataState?.data != nil :
                    self.addTaskState = AddTaskState(isLoading: false,
                                                  error: "",
                                                  data: true)
                    self.appendData(task: task)
                case dataState?.message != nil:
                    self.addTaskState = AddTaskState(isLoading: false,
                                                        error: dataState?.message ?? "",
                                                        data: false)
                    print("SahatTest",dataState?.message ?? "")
                default: return
                }
            }
        )
        
    }
    
    
    func getTasks(){
        self.getTask.execute().collectFlow(
            coroutineScope: nil,
            callback: { dataState in
                switch dataState != nil {
                case dataState?.isLoading:
                    self.taskListState = TaskListState(isLoading: dataState?.isLoading ?? false,
                                                       error: "",
                                                       data: [Task]())
                case dataState?.data != nil:
                    self.taskListState = TaskListState(isLoading: false,
                                                       error: "",
                                                       data: dataState?.data as! [Task])
                case dataState?.message != nil:
                    self.taskListState = TaskListState(isLoading: false,
                                                       error: dataState?.message ?? "",
                                                       data: [Task]())
                default: return
                }
                
            })
    }
    
    

    
    private func appendData(task: Task){
        let currentState = (self.taskListState.copy() as! TaskListState)
        var appendNewData = currentState.data
        appendNewData.append(task)
        self.taskListState = self.taskListState.doCopy(
            isLoading: currentState.isLoading,
            error: currentState.error,
            data: appendNewData
        )
        
    }
    

}
