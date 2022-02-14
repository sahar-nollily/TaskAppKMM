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
    
    enum State{
    case Error(message: String)
    case Loading
    case DoNothing
    }
    
    var currentState = State.Loading

    private let databaseModule = DatabaseModule()
    private var usecaseModule : UsecaseModule
    private let addTask: AddTask
    private let getTask: GetTasks
    
    @Published var taskListState: TaskListState = TaskListState()
    @Published var addTaskState: AddTaskState = AddTaskState()
    
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
                    self.addTaskState.isLoading = dataState?.isLoading ?? false
                case dataState?.data != nil :
                    self.addTaskState.data = true
                    self.appendData(task: task)
                    self.currentState = State.DoNothing
                case dataState?.message != nil:
                    self.currentState = State.Error(message: dataState?.message ?? "")
                    self.addTaskState.error = dataState?.message ?? ""
                    print("SahatTest",dataState?.message ?? "")
                default: self.currentState = State.DoNothing
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
                    self.currentState = State.Loading
                case dataState?.data != nil:
                    self.taskListState.data = dataState?.data as! [Task]
                    self.currentState = State.DoNothing
                case dataState?.message != nil:
                    self.currentState = State.Error(message: dataState?.message ?? "")
                default: self.currentState = State.DoNothing
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
