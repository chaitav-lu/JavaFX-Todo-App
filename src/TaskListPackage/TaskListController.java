package TaskListPackage;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

public class TaskListController {

	private ObservableList<Task> tasks;    // To store tasks
    private Task selectedValue;    // To store currently selected task

    @FXML
    private TextField titleField;

    @FXML
    private DatePicker dateField;

    @FXML
    private ComboBox<Priority> priorityField;
    
    @FXML
    private ComboBox<Occurence> occurenceField;
    
    @FXML 
    private ComboBox<Tag> tagField;

    @FXML
    private Button addBtn;

    @FXML
    private Button completeBtn;

    @FXML
    private Button deleteBtn;
    
    @FXML
    private Button editBtn;
    
    @FXML
    private Button clearBtn;

    // To display task list
    @FXML
    private ListView<Task> taskList;

    @FXML
    private void onAddTask(ActionEvent event) {
    	// To handle adding a new task
        String title = titleField.getText();
        String taskDate = dateField.getValue().toString();
        Priority priority = priorityField.getValue();
        Occurence occurence = occurenceField.getValue();
        Tag tag = tagField.getValue();

        if (title.isEmpty() || taskDate.isEmpty() || priority == null || occurence == null || tag == null) {
            displayAlert("Please fill in all fields.");
            return;
        }

        Task newData = new Task(title, dateField.getValue(), priority, occurence, tag);
        tasks.add(newData);

        clearData();
    }

    @FXML
    private void onCompleteTask(ActionEvent event) {
    	// To handle marking a task as complete
        if (selectedValue != null) {
            selectedValue.setCompleted(true);
            taskList.refresh();
            clearData();
        } else {
            displayAlert("Please select a task to mark as complete.");
        }
    }

    @FXML
    private void onDeleteTask(ActionEvent event) {
    	// To handle deleting a task
        Task selectedValue = taskList.getSelectionModel().getSelectedItem();
        if (selectedValue != null) {
            tasks.remove(selectedValue);
            clearData();
        } else {
            displayAlert("Please select a task to delete.");
        }
    }
    
    @FXML
    private void onEditTask() {
    	// To handle editing a task
        if (selectedValue != null) {
            String title = titleField.getText();
            String taskDate = dateField.getValue().toString();
            Priority priority = priorityField.getValue();
            Occurence occurence = occurenceField.getValue();
            Tag tag = tagField.getValue();
            
            if (title.isEmpty() || taskDate.isEmpty() || priority == null || occurence == null || tag == null) {
                displayAlert("Please fill in all fields.");
                return;
            }

            selectedValue.setTitle(title);
            selectedValue.setDate(dateField.getValue());
            selectedValue.setPriority(priority);
            selectedValue.setOccurence(occurence);
            selectedValue.setTag(tag);

            taskList.refresh();
            clearData();
        } else {
            displayAlert("Please select a task to edit.");
        }
    }
    
    @FXML
    private void onClear() {
    	// To handle clear button event
        clearData();
    }

    // To clear input fields and selected list item
    private void clearData() {
        taskList.getSelectionModel().clearSelection();
        titleField.clear();
        dateField.getEditor().clear();
        priorityField.getSelectionModel().clearSelection();
        occurenceField.getSelectionModel().clearSelection();
        tagField.getSelectionModel().clearSelection();
    }
    
    // To display alerts
    private void displayAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    // To initialize the controller
    public void initialize() {
        // Initialize the tasks list
        tasks = FXCollections.observableArrayList();

        // Initialize the combo boxes with values
        priorityField.setItems(FXCollections.observableArrayList(Priority.values()));
        occurenceField.setItems(FXCollections.observableArrayList(Occurence.values()));
        tagField.setItems(FXCollections.observableArrayList(Tag.values()));

        // Set the tasks list to the ListView
        taskList.setItems(tasks);

        // Customize the ListView cell factory
        taskList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
            	super.updateItem(task, empty);
                if (empty || task == null || task.getTitle() == null || task.getDate().toString().isEmpty() || task.getPriority() == null || task.getOccurence() == null || task.getTag() == null) {
                    setText(null);
                } else setText(task.toString());
            }
        });
        
        // To add listener for selection changes in the ListView
        taskList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedValue = newValue;
            if (selectedValue != null) {
                titleField.setText(selectedValue.getTitle());
                dateField.getEditor().setText(selectedValue.getDate().toString());
                dateField.setValue(selectedValue.getDate());
                priorityField.setValue(selectedValue.getPriority());
                occurenceField.setValue(selectedValue.getOccurence());
                tagField.setValue(selectedValue.getTag());
            }
        });
    }
}
