package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> map1 = getData(readFile(filePath1));
        Map<String, Object> map2 = getData(readFile(filePath2));

        return getDifference(map1, map2);
    }

    private static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        return Files.readString(path);
    }

    private static Map<String, Object> getData(String content) throws Exception {
        if (content.isEmpty()) {
            return new HashMap<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<>() {
        });
    }

    private static String getDifference(Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder sb = new StringBuilder("{\n");
        Set<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(map2.keySet());
        for (String key : keySet) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))) {

                    sb.append("    ").append(key).append(": ").append(map1.get(key)).append("\n");
                } else {

                    sb.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
                    sb.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");

                }
            } else if (map1.containsKey(key) && !map2.containsKey(key)) {

                sb.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");

            } else if (!map1.containsKey(key) && map2.containsKey(key)) {

                sb.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");

            }


        }
        sb.append("}");
        return sb.toString();
    }
}
