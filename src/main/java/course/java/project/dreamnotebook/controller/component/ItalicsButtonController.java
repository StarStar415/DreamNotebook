package course.java.project.dreamnotebook.controller.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ItalicsButtonController {
//    @FXML
//    private ImageView editButton;

    @FXML
    private ImageView testImg;

    public void initialize() throws FileNotFoundException {
        System.out.println("ItalicsButtonController create");
        System.out.println();
//        setImage(String.valueOf(getClass().getResource("/images/edit-view/italic.png")));
//        testImg = new ImageView(new Image("file:"+String.valueOf(getClass().getResource("/images/edit-view/italic.png"))));
    }

//    private void setImage(String imagePath) throws FileNotFoundException {
//        Image image = new Image("file:"+imagePath);
//        ImageView imageView = new ImageView(image);
//        editButton.setGraphic(imageView);
//        editButton.setText("");
//    }

//    public void setText(String newText){
//        editButton.setText(newText);
//    }
}
