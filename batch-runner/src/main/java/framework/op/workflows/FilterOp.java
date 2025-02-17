package framework.op.workflows;

import entity.PipelineProto;
import utils.YamlUtils;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -16 15:50
 * @Modified By:
 */
public class FilterOp implements WorkFlowOp{
    @Override
    public void create(Map<String, Object> config, PipelineProto.Workflow.Builder workflowBuilder) {
        PipelineProto.Workflow.Filter.Builder filterBuilder = PipelineProto.Workflow.Filter.newBuilder();
        String condition = YamlUtils.getString(config, "condition");
        workflowBuilder.setFilter(filterBuilder.build());
    }
}
