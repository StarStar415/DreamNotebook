package course.java.project.dreamnotebook.controller.component.editFunction;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchController implements EditFunction{
    private TextArea textArea;
    private List<Integer> searchResults;
    private int currentResultIndex;
    private String searchText;

    public SearchController(TextArea textArea){
        this.textArea = textArea;
        this.searchResults = new ArrayList<>();
        this.currentResultIndex = -1;
        this.searchText = "";

        // 監聽按鍵事件，支援切換匹配結果
        textArea.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.F) {
                goToNextResult();
                event.consume();
            }
        });
    }

    public void run() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("搜尋文字");
        dialog.setHeaderText("按 Ctrl+F 可以在多個搜尋結果間切換");
        dialog.setContentText("請輸入要搜尋的文字:");

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            searchText = result.get();
            if (searchText.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("搜尋結果");
                alert.setHeaderText(null);
                dialog.setHeaderText("按Ctrl+F可以在多個搜尋結果間切換");
                alert.setContentText("請輸入要搜尋的文字!");
                alert.showAndWait();
                return;
            }

            searchResults.clear();
            currentResultIndex = -1;

            String content = textArea.getText();
            int index = content.indexOf(searchText);
            while (index >= 0) {
                searchResults.add(index);
                index = content.indexOf(searchText, index + 1);
            }

            if (searchResults.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("搜尋結果");
                alert.setHeaderText(null);
                alert.setContentText("找不到指定的文字!");
                alert.showAndWait();
            } else {
                goToNextResult();
            }
        };
    }

    private void goToNextResult() {
        if (!searchResults.isEmpty()) {
            currentResultIndex = (currentResultIndex + 1) % searchResults.size();
            int selectedIndex = searchResults.get(currentResultIndex);

            if (searchText != null && !searchText.isEmpty()) {
                int existingIndex = textArea.getText().indexOf(searchText);
                if (selectedIndex == existingIndex) {
                    textArea.selectRange(selectedIndex, selectedIndex + searchText.length());
                    textArea.requestFocus();
                    return;
                }
            }

//            textArea.deselect();
            System.out.println(searchText);
            System.out.println(searchText.length());
            textArea.selectRange(selectedIndex, selectedIndex + searchText.length());
            textArea.requestFocus();
        }
    }
}
