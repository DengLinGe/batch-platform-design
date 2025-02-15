package utils;

import java.util.List;
import java.util.Map;

public class YamlUtils {

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