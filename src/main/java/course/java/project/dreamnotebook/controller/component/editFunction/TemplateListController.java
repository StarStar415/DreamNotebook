package course.java.project.dreamnotebook.controller.component.editFunction;

import course.java.project.dreamnotebook.controller.component.editFunction.DeleteController;
import course.java.project.dreamnotebook.controller.component.editFunction.EditFunction;
import course.java.project.dreamnotebook.controller.page.MainController;
import course.java.project.dreamnotebook.controller.page.NotebookEditController;
import course.java.project.dreamnotebook.object.Notebook;
import course.java.project.dreamnotebook.utils.RandomColor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;


public class TemplateListController implements Initializable {
    @FXML
    private VBox vbox;

    @FXML
    private Label titleLabel;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    private final Path templateDir = Path.of("src/main/resources/TemplateFiles");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int gridRow = 0;
        int gridCol = 1;

        // 設置 GridPane 的行寬為 GridPane 寬度的1/4
        for (int i = 0; i < 4; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(25);
            gridPane.getColumnConstraints().add(colConstraints);
        }

        //讀取所有 template 的 json 檔案
        File[] files = new File("src/main/resources/TemplateFiles").listFiles();

        //
        ArrayList<Notebook> templates = new ArrayList<Notebook>();
        for (File file : files) {
            templates.add(Notebook.readFromJsonFile(file));
        }

        // 把 Template 資料 render 到 GridPane
        for (Notebook nowTemplate : templates) {
            Node previewElement = generatePreviewElement(nowTemplate, false);

            putNodeToGridPane(gridPane, previewElement, gridRow, gridCol);

            // 設定下一個Label的位置
            gridCol++;
            if (gridCol == 4) {
                gridCol = 0;
                gridRow++;
            }
        }
    }

    private Node generatePreviewElement(Notebook template, boolean newNotebook){
        // 生成外型
        Label previewElement = new Label(template.getTitle());
        StackPane previewStackPane = new StackPane();
        HBox buttonHBox = new HBox();

        // 設置底圖
        previewStackPane.getChildren().add(createRectangle());
        previewElement.setGraphic(previewStackPane);

        // set hover cursor
        previewElement.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        previewElement.setCursor(Cursor.HAND);

        // 生成點擊功能
        previewElement.setOnMouseClicked(e -> {
            System.out.println("click");

            // 建立一個新的FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/course/java/project/dreamnotebook/page/template-list-view.fxml"));

            try {
                loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // 取得FXMLController//我要在這裡用一個TTemplateEditController
            NotebookEditController editController = loader.getController();

            // 傳送內容給 EditView 的 Controller//要改
            editController.setNotebook(template);

            Node fxmlNode = loader.getRoot();
            HBox.setHgrow(fxmlNode, Priority.ALWAYS);
            MainController.subScreenRoot.getChildren().setAll(fxmlNode);
        });
        return previewElement;
    }

    private void putNodeToGridPane(GridPane gridPane, Node previewElement, int row, int col){
        GridPane.setRowIndex(previewElement, row);
        GridPane.setColumnIndex(previewElement, col);
        GridPane.setMargin(previewElement, new Insets(10));
        gridPane.getChildren().add(previewElement);
        GridPane.setHalignment(previewElement, javafx.geometry.HPos.CENTER);
    }

    // 建立隨機顏色的矩形
    private Rectangle createRectangle() {
        double width = 100;
        double height = 150;
        Rectangle rectangle = new Rectangle(width, height);
        Color color = Color.web("7FE77C");
        color = RandomColor.generateSimilarColor(color, 0.1);
        rectangle.setFill(color);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        return rectangle;
    }
}