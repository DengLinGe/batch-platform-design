package framework.creator;

import entity.PipelineProto;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -15 22:45
 * @Modified By:
 */
public interface Creator {
    void createPipeline(PipelineProto.Pipeline.Builder pipelineBuilder, Map<String, Object> config);
}
