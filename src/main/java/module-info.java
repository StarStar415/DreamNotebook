module course.java.project.dreamnotebook {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires com.fasterxml.jackson.databind;
    requires javafx.web;
    requires markdown4j;
    requires javafx.media;

    opens course.java.project.dreamnotebook to javafx.fxml;
    exports course.java.project.dreamnotebook;
    exports course.java.project.dreamnotebook.controller.component;
    opens course.java.project.dreamnotebook.controller.component to javafx.fxml;
    exports course.java.project.dreamnotebook.utils;
    opens course.java.project.dreamnotebook.utils to javafx.fxml;
    exports course.java.project.dreamnotebook.object;
    opens course.java.project.dreamnotebook.object to javafx.fxml;
    exports course.java.project.dreamnotebook.controller.page;
    opens course.java.project.dreamnotebook.controller.page to javafx.fxml;
}