package course.java.project.dreamnotebook.controller.component.editFunction;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;

public class ChangeTextColorController implements EditFunction {
    private TextArea textArea;
    private ColorPicker colorPicker;
    public ChangeTextColorController(TextArea textArea, ColorPicker colorPicker ){
        this.textArea = textArea;
        this.colorPicker = colorPicker;
    }

    public void run(){
        String selectedText = textArea.getSelectedText();
        String selectedColor = colorPicker.getValue().toString().substring(2);
        String highlightText = "<span style='color:#"+selectedColor+";'>" + selectedText + "</span>";
        textArea.replaceSelection(highlightText);
    }
}
