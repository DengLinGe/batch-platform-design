package framework.op.sinks;

import entity.PipelineProto;
import utils.YamlUtils;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -16 21:43
 * @Modified By:
 */
public class JsonSinkOp implements SinkOp{
    @Override
    public void create(Map<String, Object> config, PipelineProto.Sink.Builder sinkBuilder) {
        PipelineProto.Sink.JsonSink.Builder jsonSinkBuilder = PipelineProto.Sink.JsonSink.newBuilder();
        String path = YamlUtils.getString(config, "path");
        if (path != null) {
            jsonSinkBuilder.setPath(path);
        }

        if (config.containsKey("multiLine")) {
            jsonSinkBuilder.setMultiLine(YamlUtils.getBoolean(config, "multiLine"));
        }
        sinkBuilder.setJson(jsonSinkBuilder.build());
    }
}
