package course.java.project.dreamnotebook.controller.component.editFunction;

import javafx.scene.control.TextArea;

public class BoldController implements EditFunction {
    private TextArea textArea;
    public BoldController(TextArea textArea){
        this.textArea = textArea;
    }

    public void run(){
        String selectedText = textArea.getSelectedText();
        String boldText = "**" + selectedText + "**";
        textArea.replaceSelection(boldText);
    }
}
