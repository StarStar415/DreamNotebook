package course.java.project.dreamnotebook.controller.component.editFunction;

import javafx.scene.control.TextArea;

public class ItalicsController implements EditFunction {
    private TextArea textArea;
    public ItalicsController(TextArea textArea){
        this.textArea = textArea;
    }

    public void run(){
        String selectedText = textArea.getSelectedText();
        String italicText = "*" + selectedText + "*";
        textArea.replaceSelection(italicText);
    }
}
