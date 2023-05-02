package course.java.project.dreamnotebook.controller.page;

import course.java.project.dreamnotebook.object.Notebook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class NotebookListController implements Initializable {
    @FXML
    private VBox vbox;

    @FXML
    private Label titleLabel;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane gridPane;

    private final Path notebookDir = Path.of("src/main/resources/NotebookFiles");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int gridRow = 0;
        int gridCol = 1;

        // 設置 GridPane 的列寬為 GridPane 寬度的1/4
        for (int i = 0; i < 4; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(25);
            gridPane.getColumnConstraints().add(colConstraints);
        }

        // render "New Notebook" 到 GridPane
        Node newNotebook = generatePreviewElement(new Notebook("New Notebook", "", false), true);
        putNodeToGridPane(gridPane, newNotebook, 0, 0);


        // 把 Notebook 資料 render 到 GridPane
        File[] files = new File("src/main/resources/NotebookFiles").listFiles();

        for (File file : files) {
            Notebook notebook = Notebook.readFromJsonFile(file);
            Node previewElement = generatePreviewElement(notebook, false);

            putNodeToGridPane(gridPane, previewElement, gridRow, gridCol);

            // 設定下一個Label的位置
            gridCol++;
            if (gridCol == 4) {
                gridCol = 0;
                gridRow++;
            }
        }
    }

    private Node generatePreviewElement(Notebook notebook, boolean newNotebook){
        // 生成外型
        Label previewElement = new Label(notebook.getTitle());
        if (newNotebook){
            previewElement.setGraphic(createNewNotebookGraphic());
        }
        else{
            previewElement.setGraphic(createRectangle());
        }

        // set hover cursor
        previewElement.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        previewElement.setCursor(Cursor.HAND);

        // 生成點擊功能
        previewElement.setOnMouseClicked(e -> {
            System.out.println("click");

            // 建立一個新的FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/course/java/project/dreamnotebook/page/notebook-edit-view.fxml"));

            try {
                loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // 取得FXMLController
            NotebookEditController editController = loader.getController();

            // 傳送內容給 EditView 的 Controller
            editController.setNotebook(notebook);

            Node fxmlNode = loader.getRoot();
            HBox.setHgrow(fxmlNode, Priority.ALWAYS);
            MainController.subScreenRoot.getChildren().setAll(fxmlNode);
        });
        return previewElement;
    }

    private void putNodeToGridPane(GridPane gridPane, Node previewElement, int row, int col){
        GridPane.setRowIndex(previewElement, row);
        GridPane.setColumnIndex(previewElement, col);
        GridPane.setMargin(previewElement, new javafx.geometry.Insets(10));
        gridPane.getChildren().add(previewElement);
        GridPane.setHalignment(previewElement, javafx.geometry.HPos.CENTER);
    }

    // 建立隨機顏色的矩形
    private Rectangle createRectangle() {
        double width = 100;
        double height = 150;
        Rectangle rectangle = new Rectangle(width, height);
        Color color = Color.rgb((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
        rectangle.setFill(color);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        return rectangle;
    }

    // 創建黑色十字圖形
    private Node createNewNotebookGraphic() {
        Group group = new Group();
        double width = 100;
        double height = 150;
        double halfLength = 15;
        double lineWidth = 4;
        double padding = 10;

        // Create background rectangle with padding
        Rectangle background = new Rectangle(width, height, Color.WHITE);
        background.setStroke(Color.BLACK);
        background.setStrokeWidth(1);
        background.setArcHeight(20);
        background.setArcWidth(20);
        background.setX(-padding);
        background.setY(-padding);

        // Create horizontal and vertical lines for the cross
        Line horizontalLine = new Line(width/2-padding, height/2-padding-halfLength, width/2-padding, height/2-padding+halfLength);
        horizontalLine.setStroke(Color.BLACK);
        horizontalLine.setStrokeWidth(lineWidth);

        Line verticalLine = new Line(width/2-padding-halfLength, height/2-padding, width/2-padding+halfLength, height/2-padding);
        verticalLine.setStroke(Color.BLACK);
        verticalLine.setStrokeWidth(lineWidth);

        group.getChildren().addAll(background, horizontalLine, verticalLine);
        return group;
    }

}