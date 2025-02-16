package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class YamlUtils {


    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        // 从 resources 文件夹中获取指定 YAML 文件的输入流
        InputStream inputStream = YamlUtils.class
                .getClassLoader()
                .getResourceAsStream("test.yaml");
        Map<String, Object> data = yaml.load(inputStream);
        System.out.println(data);

    }

    /**
     * 从 Map 中获取指定 key 对应的 String 类型的值
     *
     * @param data 包含 YAML 数据的 Map
     * @param key  要获取值的键
     * @return 对应的 String 值，如果不存在或类型不匹配则返回 null
     */
    public static String getString(Map<String, Object> data, String key) {
        if (data.containsKey(key)) {
            Object value = data.get(key);
            if (value instanceof String) {
                return (String) value;
            }
        }
        return null;
    }

    public static String getStringOrDefault(Map<String, Object> data, String key, String defaultValue) {
        String value = getString(data, key);
        return value == null ? defaultValue : value;
    }

    public static boolean getBoolean(Map<String, Object> data, String key) {
        if (data.containsKey(key)) {
            String value = (String)data.get(key);
            if (value!= null && value == "true") {
                return true;
            }
        }
        return false;
    }

    /**
     * 从 Map 中获取指定 key 对应的 Integer 类型的值
     *
     * @param data 包含 YAML 数据的 Map
     * @param key  要获取值的键
     * @return 对应的 Integer 值，如果不存在或类型不匹配则返回 null
     */
    public static Integer getInteger(Map<String, Object> data, String key) {
        if (data.containsKey(key)) {
            Object value = data.get(key);
            if (value instanceof Integer) {
                return (Integer) value;
            }
        }
        return null;
    }

    /**
     * 从 Map 中获取指定 key 对应的 List<Map<String, Object>> 类型的值
     *
     * @param data 包含 YAML 数据的 Map
     * @param key  要获取值的键
     * @return 对应的 List<Map<String, Object>> 值，如果不存在或类型不匹配则返回 null
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> getListMap(Map<String, Object> data, String key) {
        if (data.containsKey(key)) {
            Object value = data.get(key);
            if (value instanceof List) {
                try {
                    return (List<Map<String, Object>>) value;
                } catch (ClassCastException e) {
                    return null;
                }
            }
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public static List<String> getList(Map<String, Object> data, String key) {
        if (data.containsKey(key)) {
            Object value = data.get(key);
            if (value instanceof List) {
                try {
                    return (List<String>) value;
                } catch (ClassCastException e) {
                    return null;
                }
            }
        }
        return null;
    }
    /**
     * 从 Map 中获取指定 key 对应的 Map<String, Object> 类型的值
     *
     * @param data 包含 YAML 数据的 Map
     * @param key  要获取值的键
     * @return 对应的 Map<String, Object> 值，如果不存在或类型不匹配则返回 null
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMap(Map<String, Object> data, String key) {
        if (data.containsKey(key)) {
            Object value = data.get(key);
            if (value instanceof Map) {
                return (Map<String, Object>) value;
            }
        }
        return null;
    }



}