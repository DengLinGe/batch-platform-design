
import creator.SinkCreator;
import creator.SourceCreator;
import creator.WorkflowCreator;
import entity.PipelineProto.Pipeline;

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

    YamlTransformer() {
    }

    YamlTransformer(Map<String, Object> config) {
        this.config = config;
    }

    public Pipeline transformToPipeline() {
        Pipeline.Builder pipelineBuilder = Pipeline.newBuilder();

        // 建造builder
        new SourceCreator().createPipeline(pipelineBuilder, config);
        new WorkflowCreator().createPipeline(pipelineBuilder, config);
//        new SinkCreator().createPipeline(pipelineBuilder, config);

        // 返回pipeline
        Pipeline pipeline = pipelineBuilder.build();
        logger.info("Pipeline created.");
        logger.info(pipeline.toString());

        return pipeline;
    }
}
