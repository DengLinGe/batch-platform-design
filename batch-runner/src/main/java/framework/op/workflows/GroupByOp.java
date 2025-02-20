package framework.op.workflows;

import entity.PipelineProto;
import utils.YamlUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -16 15:50
 * @Modified By:
 */
public class GroupByOp implements WorkFlowOp{
    @Override
    public void create(Map<String, Object> config, PipelineProto.Workflow.Builder workflowBuilder) {
        PipelineProto.Workflow.GroupBy.Builder groupByBuilder = PipelineProto.Workflow.GroupBy.newBuilder();
        List<String> columns = YamlUtils.getList(config, "columns");
        groupByBuilder.addAllColumns(columns);

        List<String> agg = YamlUtils.getList(config, "agg");
        groupByBuilder.addAllAgg(agg);

        workflowBuilder.setGroupBy(groupByBuilder.build());

    }
}
