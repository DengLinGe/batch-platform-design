import entity.PipelineProto;
import framework.YamlTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import runner.BatchRunner;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -15 16:41
 * @Modified By:
 */
public class Boot {
    static Logger logger = LoggerFactory.getLogger(Boot.class);

    public static void main(String[] args) {
//        // 解析命令行参数
//        Map<String, String> params = parseCommandLineArgs(args);
//
//        // 获取配置文件名
//        String configFileName = params.get("configName");
//        if (configFileName == null) {
//            System.err.println("请通过 -configName=your_config_file.yaml 指定 YAML 配置文件的名称。");
//            return;
//        }

        // 创建 Yaml 对象
        Yaml yaml = new Yaml();
        // 从 resources 文件夹中获取指定 YAML 文件的输入流
        InputStream inputStream = Boot.class
                .getClassLoader()
                .getResourceAsStream("test.yaml");
//                .getResourceAsStream(configFileName);
//        if (inputStream == null) {
//            throw new RuntimeException("找不到配置文件: " + configFileName);
//        }

        // 加载 YAML 文件内容到一个 Map 对象中
        Map<String, Object> data = yaml.load(inputStream);
        YamlTransformer yamlTransformer = new YamlTransformer(data);
        PipelineProto.Pipeline pipeline = yamlTransformer.transformToPipeline();

        BatchRunner runner = new BatchRunner(pipeline);
        runner.evaluate(pipeline);


    }

    /**
     * 解析命令行参数，将 key=val 形式的参数解析到 Map 中
     * @param args 命令行参数数组
     * @return 包含参数键值对的 Map
     */
    private static Map<String, String> parseCommandLineArgs(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (String arg : args) {
            if (arg.startsWith("-") && arg.contains("=")) {
                String[] parts = arg.substring(1).split("=", 2);
                if (parts.length == 2) {
                    params.put(parts[0], parts[1]);
                } else {
                    throw new RuntimeException("Invalid command line argument: " + arg);
                }

            }
        }
        return params;
    }
}
