package op.sinks;

import entity.PipelineProto;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -16 21:36
 * @Modified By:
 */
public interface SinkOp {

    void create(Map<String, Object> config, PipelineProto.Sink.Builder sinkBuilder);
}
