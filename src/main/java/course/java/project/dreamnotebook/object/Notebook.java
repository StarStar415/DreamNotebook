package course.java.project.dreamnotebook.object;

import course.java.project.dreamnotebook.utils.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Notebook {
    // title 就是文件名稱
    private String title;
    private String content;

    public Notebook(String title, String content) {
        this.title = title;
        this.content = content;
    }

    static public Notebook readFromJsonFile(File file){
        Map<String, Object> map = null;
        try {
            map = JsonUtils.fromJson(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String fileName = file.getName(); // 取得完整檔案名稱，例如 "file.ext"

        int dotIndex = fileName.lastIndexOf("."); // 取得最後一個"."字元的位置

        fileName = fileName.substring(0, dotIndex); // 取得不包含副檔名的檔案名稱
        String title = fileName;
        String content = (String) map.get("content");
        return new Notebook(title, content);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}