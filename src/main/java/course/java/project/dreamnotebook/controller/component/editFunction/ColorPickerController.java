package course.java.project.dreamnotebook.controller.component.editFunction;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;

import java.io.PrintStream;

public class ColorPickerController implements EditFunction {
    private ColorPicker colorPicker;
    public ColorPickerController(ColorPicker colorPicker ){
        this.colorPicker = colorPicker;
    }

    public void run(){
        System.out.print("now choose color");
        System.out.print(colorPicker.getValue());
        System.out.println();
    }
}
