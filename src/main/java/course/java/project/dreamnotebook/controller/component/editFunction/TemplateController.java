package course.java.project.dreamnotebook.controller.component.editFunction;

import javafx.scene.control.TextArea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class TemplateController implements EditFunction {
    private TextArea textArea;
    public TemplateController(TextArea textArea){
        this.textArea = textArea;
    }

    public void run(){
        Stage stage = new Stage();
        stage.setTitle("Dropdown Menu");

        ObservableList<String> options = FXCollections.observableArrayList(
                "Option 1",
                "Option 2",
                "Option 3"
        );

        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // 处理选择项变化的逻辑
            // 可以根据选择的项更新应用程序中的其他组件或执行其他操作
            System.out.println("Selected Option: " + newValue);
        });

        VBox vbox = new VBox(comboBox);
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 200, 150);
        stage.setScene(scene);
        stage.show();
    }
}
