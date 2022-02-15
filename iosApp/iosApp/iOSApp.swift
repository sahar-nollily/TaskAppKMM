import SwiftUI
import shared

@main
struct iOSApp: App {
    
    @ObservedObject var sharedViewModel: SharedViewModel = SharedViewModel()

        
	var body: some Scene {
		WindowGroup {
            
            if(self.sharedViewModel.addTaskState.error != ""){
                getErrorButton(meesage: self.sharedViewModel.addTaskState.error)
            }

            AddTaskScreen(onAddNewTask:{ (newTask: Task) in
                self.sharedViewModel.addTask(task: newTask)
            })
            
            
            TaskListScreen(taskListState: self.sharedViewModel.taskListState)

		}
	}
    
    
    @ViewBuilder private func getErrorButton(meesage: String) -> some View {
        Text("* \(meesage)")
            .padding(.top, 10)
            .foregroundColor(.red)
        
    }

}
