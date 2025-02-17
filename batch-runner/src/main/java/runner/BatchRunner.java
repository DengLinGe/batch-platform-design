package runner;

import entity.Node;
import entity.PipelineProto;
import org.apache.spark.sql.SparkSession;
import runner.evaluator.Evaluator;

import java.util.*;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -17 13:14
 * @Modified By:
 */
public class BatchRunner {
    public RunnerContext context;
    public SparkSession sparkSession;


    public BatchRunner(PipelineProto.Pipeline pipeline) {
        this.context = new RunnerContext();
//        this.sparkSession = SparkSession.builder().appName("BatchRunner").master("local[*]").getOrCreate();
        evaluate(pipeline);
    }


    // 读取Pipeline.proto文件，转化为对应的Node并且执行
    public void evaluate(PipelineProto.Pipeline pipeline) {
        // 将 Pipeline 转化为 Node 列表
        List<Node> nodes = convertToNodes(pipeline);
        // 构建 DAG 图
        Map<String, Node> nodeMap = buildNodeMap(nodes);
        Map<String, List<String>> dag = buildDAG(nodes);
        // 进行拓扑排序
        List<Node> sortedNodes = topologicalSort(nodeMap, dag);

        // 调用 Translator 类将 Node 转为具体执行的类
//        Translator translator = new Translator();
//        for (Node node : sortedNodes) {
//            Evaluator evaluator = translator.translate(node);
//            evaluator.evaluate(context);
//        }
    }

    // 将 Pipeline 转化为 Node 列表
    private static List<Node> convertToNodes(PipelineProto.Pipeline pipeline) {
        List<Node> nodes = new ArrayList<>();

        // 处理 sources 节点
        for (PipelineProto.Source source : pipeline.getSourcesList()) {
            String uid = source.getUid();
            String operatorType = getSourceOperatorType(source);
            List<String> inputs = new ArrayList<>();
            List<String> outputs = new ArrayList<>(source.getOutputList());
            Object config = getSourceConfig(source);
            nodes.add(new Node(uid, operatorType, inputs, outputs, config));
        }

        // 处理 workflows 节点
        for (PipelineProto.Workflow workflow : pipeline.getWorkflowsList()) {
            String uid = workflow.getUid();
            String operatorType = getWorkflowOperatorType(workflow);
            List<String> inputs = new ArrayList<>(workflow.getInputList());
            List<String> outputs = new ArrayList<>(workflow.getOutputList());
            Object config = getWorkflowConfig(workflow);
            nodes.add(new Node(uid, operatorType, inputs, outputs, config));
        }

        // 处理 sinks 节点
        for (PipelineProto.Sink sink : pipeline.getSinksList()) {
            String uid = sink.getUid();
            String operatorType = getSinkOperatorType(sink);
            List<String> inputs = Collections.singletonList(sink.getInput());
            List<String> outputs = new ArrayList<>();
            Object config = getSinkConfig(sink);
            nodes.add(new Node(uid, operatorType, inputs, outputs, config));
        }

        return nodes;
    }

    // 获取 source 节点的算子类型
    private static String getSourceOperatorType(PipelineProto.Source source) {
        if (source.hasMysql()) {
            return "mysql_source";
        } else if (source.hasCsv()) {
            return "csv_source";
        }
        return null;
    }

    // 获取 source 节点的配置信息
    private static Object getSourceConfig(PipelineProto.Source source) {
        if (source.hasMysql()) {
            return source.getMysql();
        } else if (source.hasCsv()) {
            return source.getCsv();
        }
        return null;
    }

    // 获取 workflow 节点的算子类型
    private static String getWorkflowOperatorType(PipelineProto.Workflow workflow) {
        if (workflow.hasSelect()) {
            return "select";
        } else if (workflow.hasFilter()) {
            return "filter";
        } else if (workflow.hasSql()) {
            return "sql";
        }
        return null;
    }

    // 获取 workflow 节点的配置信息
    private static Object getWorkflowConfig(PipelineProto.Workflow workflow) {
        if (workflow.hasSelect()) {
            return workflow.getSelect();
        } else if (workflow.hasFilter()) {
            return workflow.getFilter();
        } else if (workflow.hasSql()) {
            return workflow.getSql();
        }
        return null;
    }

    // 获取 sink 节点的算子类型
    private static String getSinkOperatorType(PipelineProto.Sink sink) {
        if (sink.hasJdbc()) {
            return "jdbc_sink";
        } else if (sink.hasCsv()) {
            return "csv_sink";
        }
        return null;
    }

    // 获取 sink 节点的配置信息
    private static Object getSinkConfig(PipelineProto.Sink sink) {
        if (sink.hasJdbc()) {
            return sink.getJdbc();
        } else if (sink.hasCsv()) {
            return sink.getCsv();
        }
        return null;
    }

    // 构建节点映射
    private static Map<String, Node> buildNodeMap(List<Node> nodes) {
        Map<String, Node> nodeMap = new HashMap<>();
        for (Node node : nodes) {
            nodeMap.put(node.uid, node);
        }
        return nodeMap;
    }

    // 构建 DAG 图
    private static Map<String, List<String>> buildDAG(List<Node> nodes) {
        Map<String, List<String>> dag = new HashMap<>();
        for (Node node : nodes) {
            // 为每一个node创建一个空的输出列表
            dag.put(node.uid, new ArrayList<>());
            // 遍历该节点的输出列表，将输出列表中的每一个输出添加到dag中
            for (String output : node.outputs) {
                dag.computeIfAbsent(node.uid, k -> new ArrayList<>()).add(output);
            }
        }
        return dag;
    }

    // 拓扑排序
    private static List<Node> topologicalSort(Map<String, Node> nodeMap, Map<String, List<String>> dag) {
        List<Node> sortedNodes = new ArrayList<>();
        Map<String, Integer> inDegree = new HashMap<>();

        // 计算每个节点的入度
        for (Map.Entry<String, List<String>> entry : dag.entrySet()) {
            String node = entry.getKey();
            inDegree.putIfAbsent(node, 0);
            for (String neighbor : entry.getValue()) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        // 初始化队列，将入度为 0 的节点加入队列
        Queue<String> queue = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        // 进行拓扑排序
        while (!queue.isEmpty()) {
            String node = queue.poll();
            sortedNodes.add(nodeMap.get(node));
            for (String neighbor : dag.getOrDefault(node, new ArrayList<>())) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return sortedNodes;
    }
}
