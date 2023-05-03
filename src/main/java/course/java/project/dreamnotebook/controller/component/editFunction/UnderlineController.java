package course.java.project.dreamnotebook.controller.component.editFunction;

import javafx.scene.control.TextArea;

public class UnderlineController implements EditFunction {
    private TextArea textArea;
    public UnderlineController(TextArea textArea){
        this.textArea = textArea;
    }

    public void run(){
        String selectedText = textArea.getSelectedText();
        String underlineText = "<u>" + selectedText + "</u>";
        textArea.replaceSelection(underlineText);
    }
}
