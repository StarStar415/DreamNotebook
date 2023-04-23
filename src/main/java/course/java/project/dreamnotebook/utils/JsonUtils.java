package course.java.project.dreamnotebook.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> fromJson(File file) throws IOException {
        return objectMapper.readValue(file, Map.class);
    }

}
