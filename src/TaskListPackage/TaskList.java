package TaskListPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class TaskList extends Application {
	@Override
	 public void start(Stage stage) throws Exception {
	     Parent root = FXMLLoader.load(getClass().getResource("/resources/TaskList.fxml"));
	     
	     Scene scene = new Scene(root); // attach scene graph to scene
	     stage.setTitle("To-Do List Application"); // displayed in window's title bar
	     stage.setScene(scene); // attach scene to stage
	     stage.show(); // display the stage
	 }

	 public static void main(String[] args) {
	     // create a TaskList object and call its start method
	     launch(args);
	 }
}
