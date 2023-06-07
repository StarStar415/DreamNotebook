package course.java.project.dreamnotebook;

import course.java.project.dreamnotebook.controller.page.NotebookListController;

import java.io.File;
import java.net.URISyntaxException;

public class Main {
    public static void main(String args[]){
        String[] directories = { "NotebookFiles", "TemplateFiles", "upload" ,"music"};  // 資料夾路徑數組

        for (String directoryPath : directories) {
            File jarFile = null;
            try {
                jarFile = new File(NotebookListController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            File parentDirectory = jarFile.getParentFile();
            File directory = new File(parentDirectory, directoryPath);

            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("資料夾 " + directoryPath + " 已成功創建");
                } else {
                    System.out.println("無法創建資料夾 " + directoryPath);
                }
            } else {
                System.out.println("資料夾 " + directoryPath + " 已存在");
            }
        }
        DreamNotebookApplication.main(args);
    }
}
