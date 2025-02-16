package op.sources;

import entity.PipelineProto;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -16 14:52
 * @Modified By:
 */
public interface SourceOp {

    void create(Map<String, Object> config, PipelineProto.Source.Builder sourceBuilder);
}
