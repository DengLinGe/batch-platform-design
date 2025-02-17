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
public class CsvSinkOp implements SinkOp{
    @Override
    public void create(Map<String, Object> config, PipelineProto.Sink.Builder sinkBuilder) {
        PipelineProto.Sink.CsvSink.Builder csvSinkBuilder = PipelineProto.Sink.CsvSink.newBuilder();
        String path = YamlUtils.getString(config, "path");
        boolean header = YamlUtils.getBoolean(config, "header");
        String delimiter = YamlUtils.getStringOrDefault(config, "delimiter", ",");


        if (path != null) {
            csvSinkBuilder.setPath(path);
        }
        csvSinkBuilder.setHeader(header);
        csvSinkBuilder.setDelimiter(delimiter);
        if (config.containsKey("quote")) {
            csvSinkBuilder.setQuote((String) config.get("quote"));
        }
        if (config.containsKey("escape")) {
            csvSinkBuilder.setEscape((String) config.get("escape"));
        }

        sinkBuilder.setCsv(csvSinkBuilder.build());
    }
}
