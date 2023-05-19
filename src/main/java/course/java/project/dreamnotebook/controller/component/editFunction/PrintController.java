package course.java.project.dreamnotebook.controller.component.editFunction;

import course.java.project.dreamnotebook.controller.page.MainController;
import course.java.project.dreamnotebook.object.Notebook;
import course.java.project.dreamnotebook.object.Toast;
import course.java.project.dreamnotebook.object.ToastAnimationTime;
import course.java.project.dreamnotebook.object.ToastType;
import javafx.print.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.transform.Scale;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.markdown4j.Markdown4jProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PrintController implements EditFunction{
    private WebView webView;
    private TextArea textArea;

    public PrintController(WebView webView, TextArea textArea){
        this.webView = webView;
        this.textArea = textArea;
    }

    public void run(){
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            webView.getEngine().print(job);
            job.endJob();
        }

    }
}
