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
public class JoinOp implements WorkFlowOp{
    @Override
    public void create(Map<String, Object> config, PipelineProto.Workflow.Builder workflowBuilder) {
        PipelineProto.Workflow.Join.Builder joinBuilder = PipelineProto.Workflow.Join.newBuilder();
        String otherTableId = YamlUtils.getString(config, "other_table_id");
        String joinType = YamlUtils.getString(config, "join_type");
        String onCondition = YamlUtils.getString(config, "on_condition");

        if (otherTableId != null) {
            joinBuilder.setOtherTableId(otherTableId);
        }
        if (joinType != null) {
            joinBuilder.setJoinType(joinType);
        }
        if (onCondition != null) {
            joinBuilder.setOnCondition(onCondition);
        }

        workflowBuilder.setJoin(joinBuilder.build());
    }
}
