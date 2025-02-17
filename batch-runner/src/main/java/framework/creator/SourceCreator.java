package framework.creator;

import entity.PipelineProto;
import framework.op.sources.CsvSourceOp;
import framework.op.sources.MysqlSourceOp;
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
public class SourceCreator implements Creator {



    @Override
    public void createPipeline(PipelineProto.Pipeline.Builder pipelineBuilder, Map<String, Object> config) {
        List<Map<String, Object>> listMap = YamlUtils.getListMap(config, SOURCES);

        if (listMap == null || listMap.isEmpty()) {
            throw new IllegalArgumentException("Sources is empty.");
        }

        for (Map<String, Object> map : listMap) {
            PipelineProto.Source.Builder sourceBuilder = PipelineProto.Source.newBuilder();
            // 设置名字
            sourceBuilder.setUid(YamlUtils.getString(map, UID));



            // 根据sources_type关键字进行不同op的填充
            switch (Objects.requireNonNull(YamlUtils.getString(map, SOURCES_TYPE))) {
                case "csv":
                    new CsvSourceOp().create(map, sourceBuilder);
                    break;
                case "mysql":
                   new MysqlSourceOp().create(map, sourceBuilder);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown source type: " + YamlUtils.getString(map, SOURCES_TYPE));
            }

            pipelineBuilder.addSources(sourceBuilder.build());
        }






    }
}
