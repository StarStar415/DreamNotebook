package course.java.project.dreamnotebook.object;

import course.java.project.dreamnotebook.utils.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Notebook {
    // title 就是文件名稱
    private String title;
    private String content;

    private String lastModify;
    // 是否已存檔過
    // 若處在剛新建文件，未存檔過的階段，則為 false
    private Boolean hasSaved;

    public Notebook(String title, String content, Boolean hasSaved,String lastModify) {
        this.title = title;
        this.content = content;
        this.hasSaved = hasSaved;
        this.lastModify = lastModify;
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
        String lastModify = (String) map.get("lastModify");
        if(lastModify == null)lastModify = "1999-11-11 11:11:11";
//        System.out.println(lastModify);
        return new Notebook(title, content, true,lastModify);
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

    public void setLastModify(String lastModify){
        this.lastModify = lastModify;
    }

    public String getLastModify() { return lastModify; }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHasSaved(Boolean hasSaved) {
        this.hasSaved = hasSaved;
    }
    public Boolean getHasSaved(){
        return hasSaved;
    }
}