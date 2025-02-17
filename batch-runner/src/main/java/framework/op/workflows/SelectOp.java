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
public class SelectOp implements WorkFlowOp{
    @Override
    public void create(Map<String, Object> config, PipelineProto.Workflow.Builder workflowBuilder) {
        PipelineProto.Workflow.Select.Builder selectBuilder = PipelineProto.Workflow.Select.newBuilder();
        List<String> columns = YamlUtils.getList(config, "columns");
        selectBuilder.addAllColumns(columns);
        workflowBuilder.setSelect(selectBuilder.build());
    }
}
