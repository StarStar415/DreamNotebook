package course.java.project.dreamnotebook.object;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public final class Toast
{
    public static void makeText(Stage ownerStage, String toastMsg, ToastAnimationTime toastAnimationTime, ToastType toastType)
    {
        int toastDelay = toastAnimationTime.getToastDelay();
        int fadeInDelay = toastAnimationTime.getFadeInDelay();
        int fadeOutDelay = toastAnimationTime.getFadeOutDelay();

        Stage toastStage=new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        Text text = new Text(toastMsg);
        text.setFont(Font.font("Verdana", 20));
        text.setFill(Color.WHITE);

        double ownerX = ownerStage.getX();
        double ownerY = ownerStage.getY();
        toastStage.setX(ownerX + ownerStage.getWidth() / 2 - 70);
        toastStage.setY(ownerY + 50);

        StackPane root = new StackPane(text);
        switch (toastType){
            case SUCCESS -> root.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(108, 212, 146, 0.8); -fx-padding: 30px;");
            case ERROR -> root.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(141, 20, 20, 0.8); -fx-padding: 30px;");
            case INFO -> root.setStyle("-fx-background-radius: 20; -fx-background-color: rgba(78, 141, 233, 0.8); -fx-padding: 30px;");
        }
        root.setOpacity(0);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 1));
        fadeInTimeline.getKeyFrames().add(fadeInKey1);
        fadeInTimeline.setOnFinished((ae) ->
        {
            new Thread(() -> {
                try
                {
                    Thread.sleep(toastDelay);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                Timeline fadeOutTimeline = new Timeline();
                KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 0));
                fadeOutTimeline.getKeyFrames().add(fadeOutKey1);
                fadeOutTimeline.setOnFinished((aeb) -> toastStage.close());
                fadeOutTimeline.play();
            }).start();
        });
        fadeInTimeline.play();
    }
}