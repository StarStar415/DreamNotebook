package course.java.project.dreamnotebook.controller.component.editFunction;

import javafx.scene.control.TextArea;

public class DeletelineController implements EditFunction {
    private TextArea textArea;
    public DeletelineController(TextArea textArea){
        this.textArea = textArea;
    }

    public void run(){
        String selectedText = textArea.getSelectedText();
        String deletelineText = "~~" + selectedText + "~~";
        textArea.replaceSelection(deletelineText);
    }
}
