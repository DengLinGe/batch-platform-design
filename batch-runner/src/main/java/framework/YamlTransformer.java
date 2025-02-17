package framework;

import entity.PipelineProto;
import framework.creator.SinkCreator;
import framework.creator.SourceCreator;
import framework.creator.WorkflowCreator;
import entity.PipelineProto.Pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -15 17:37
 * @Modified By:
 */
public class YamlTransformer {

    final static Logger logger = Logger.getLogger(YamlTransformer.class.getName());

    Map<String, Object> config;

    public YamlTransformer() {
    }

    public YamlTransformer(Map<String, Object> config) {
        this.config = config;
    }

    public Pipeline transformToPipeline() {
        Pipeline.Builder pipelineBuilder = Pipeline.newBuilder();

        // 建造builder
        new SourceCreator().createPipeline(pipelineBuilder, config);
        new WorkflowCreator().createPipeline(pipelineBuilder, config);
        new SinkCreator().createPipeline(pipelineBuilder, config);

        // 填充输出信息
        fillOutputs(pipelineBuilder);

        // 返回pipeline
        Pipeline pipeline = pipelineBuilder.build();
        logger.info("Pipeline created.");
        logger.info(pipeline.toString());

        return pipeline;
    }


    public static void fillOutputs(PipelineProto.Pipeline.Builder pipelineBuilder) {
        // 用于存储每个 uid 被哪些后续的 workflow 或 sink 使用
        Map<String, List<String>> inputUsageMap = new HashMap<>();




        // 遍历 workflows 收集输入依赖关系
        for (PipelineProto.Workflow workflow : pipelineBuilder.getWorkflowsList()) {
            for (String input : workflow.getInputList()) {
                inputUsageMap.computeIfAbsent(input, k -> new ArrayList<>()).add(workflow.getUid());
            }
        }

        // 遍历 sinks 收集输入依赖关系
        for (PipelineProto.Sink sink : pipelineBuilder.getSinksList()) {
            String input = sink.getInput();
            inputUsageMap.computeIfAbsent(input, k -> new ArrayList<>()).add(sink.getUid());

        }

        // 填充 source 的 output 信息
        for (PipelineProto.Source.Builder sourceBuilder : pipelineBuilder.getSourcesBuilderList()) {
            String sourceUid = sourceBuilder.getUid();
            if (inputUsageMap.containsKey(sourceUid)) {
                sourceBuilder.addAllOutput(inputUsageMap.get(sourceUid));
            }
        }
        // 填充 workflow 的 output 信息
        for (PipelineProto.Workflow.Builder workflowBuilder : pipelineBuilder.getWorkflowsBuilderList()) {
            String workflowUid = workflowBuilder.getUid();
            if (inputUsageMap.containsKey(workflowUid)) {
                workflowBuilder.addAllOutput(inputUsageMap.get(workflowUid));
            }
        }
    }
}
