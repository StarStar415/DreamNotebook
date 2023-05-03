package course.java.project.dreamnotebook.controller.component.editFunction;

import javafx.scene.control.TextArea;

public class LightpenController implements EditFunction {
    private TextArea textArea;
    public LightpenController(TextArea textArea){
        this.textArea = textArea;
    }

    public void run(){
        String selectedText = textArea.getSelectedText();
        String lightpenText = "<style text>" + selectedText + "</style >";
        textArea.replaceSelection(lightpenText);
    }
}
