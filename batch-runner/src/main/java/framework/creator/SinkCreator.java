package framework.creator;

import entity.PipelineProto;
import framework.op.sinks.ConsoleSinkOp;
import framework.op.sinks.CsvSinkOp;
import framework.op.sinks.JsonSinkOp;
import framework.op.sinks.MysqlSinkOp;
import utils.YamlUtils;


import java.util.List;
import java.util.Map;
import java.util.Objects;

import static entity.Constants.*;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -15 22:44
 * @Modified By:
 */
public class SinkCreator implements Creator {
    @Override
    public void createPipeline(PipelineProto.Pipeline.Builder pipelineBuilder, Map<String, Object> config) {
        List<Map<String, Object>> listMap = YamlUtils.getListMap(config, SINKS);

        if (listMap == null || listMap.isEmpty()) {
            throw new IllegalArgumentException("Sinks is empty.");
        }

        for (Map<String, Object> map : listMap) {
            PipelineProto.Sink.Builder sinkBuilder = PipelineProto.Sink.newBuilder();
            // 设置 uid
            sinkBuilder.setUid(YamlUtils.getString(map, UID));
            // 设置 input
            sinkBuilder.setInput(YamlUtils.getString(map, INPUT));

            // 根据 sink_type 关键字进行不同 framework.op 的填充
            switch (Objects.requireNonNull(YamlUtils.getString(map, SINK_TYPE))) {
                case "csv":
                    new CsvSinkOp().create(map, sinkBuilder);
                    break;
                case "json":
                    new JsonSinkOp().create(map, sinkBuilder);
                    break;
                case "mysql":
                    new MysqlSinkOp().create(map, sinkBuilder);
                    break;

                case "console":
                    new ConsoleSinkOp().create(map, sinkBuilder);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown sink type: " + YamlUtils.getString(map, SINK_TYPE));
            }

            pipelineBuilder.addSinks(sinkBuilder.build());
        }
    }
}
