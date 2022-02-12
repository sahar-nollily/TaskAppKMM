import SwiftUI
import shared

@main
struct iOSApp: App {
    
    @ObservedObject var sharedViewModel: SharedViewModel = SharedViewModel()
        
	var body: some Scene {
		WindowGroup {

            AddTaskScreen(onAddNewTask:{ (newTask: Task) in
                self.sharedViewModel.addTask(task: newTask)
            })
            
            switch self.sharedViewModel.currentState {
            case .Error(let message):
                OnError(message: message)
                progressView.hidden()
            case .Loading:
                progressView
            case .DoNothing:
                progressView.hidden()
            }
            
            TaskListScreen(taskListState: self.sharedViewModel.taskListState)
		}
	}
    
    var progressView: some View{
        ProgressView("Loading ...")
    }
    
    struct OnError: View {
        private var message: String?
        init(
            message: String
        ){
            self.message = message
        }
      var body: some View {
          Text(self.message!)
      }
    }
}
