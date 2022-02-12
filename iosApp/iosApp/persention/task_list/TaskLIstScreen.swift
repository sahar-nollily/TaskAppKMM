//
//  TaskLIstScreen.swift
//  iosApp
//
//  Created by Sa7ar Nollily on 11/07/1443 AH.
//  Copyright © 1443 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine


struct TaskListScreen: View {
    
    private let taskListState : TaskListState    

    init(
        taskListState : TaskListState
    ){
        self.taskListState = taskListState
    }

    var body: some View {
        VStack{
            List(){
                ForEach(self.taskListState.data, id: \.self.title){
                    task in Text(task.title)
                        .padding(.all, 20.0)
                }
            }
        
            
        }
    }
}
    

