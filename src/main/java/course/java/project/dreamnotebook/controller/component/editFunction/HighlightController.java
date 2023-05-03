package course.java.project.dreamnotebook.controller.component.editFunction;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class HighlightController implements EditFunction {
    private TextArea textArea;
    private ColorPicker colorPicker;
    public HighlightController(TextArea textArea, ColorPicker colorPicker ){
        this.textArea = textArea;
        this.colorPicker = colorPicker;
    }

    public void run(){
        String selectedText = textArea.getSelectedText();
        String selectedColor = colorPicker.getValue().toString().substring(2);
        String highlightText = "<span style='background-color:#"+selectedColor+";'>" + selectedText + "</span>";
        textArea.replaceSelection(highlightText);
    }
}
