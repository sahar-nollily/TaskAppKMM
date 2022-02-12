//
//  AddTaskScreen.swift
//  iosApp
//
//  Created by Sa7ar Nollily on 10/07/1443 AH.
//  Copyright © 1443 orgName. All rights reserved.
//

import SwiftUI
import Combine
import shared


struct AddTaskScreen: View {
    private let onAddNewTask: (Task) -> Void
    @State var taskTitle: String = ""

    
    init(
        onAddNewTask: @escaping (Task) -> Void
    ){
        self.onAddNewTask = onAddNewTask
    }

    var body: some View {
        
        HStack{
            TextField("Enter Task Title", text: self.$taskTitle)
            Button(action: self.addNewTask , label: {
                Text("Add")
            })
        }
        .padding([.top, .leading, .trailing], 20.0)

    }
    
    func addNewTask(){
        self.onAddNewTask(Task(id: KotlinInt?(nil), title: String(taskTitle)))
        taskTitle = ""
        
    }
    

}
