package op.workflows;

import entity.PipelineProto;
import utils.YamlUtils;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -16 15:50
 * @Modified By:
 */
public class WithColumnOp implements WorkFlowOp{
    @Override
    public void create(Map<String, Object> config, PipelineProto.Workflow.Builder workflowBuilder) {
        PipelineProto.Workflow.WithColumn.Builder withColumnBuilder = PipelineProto.Workflow.WithColumn.newBuilder();
        String newColumnName = YamlUtils.getString(config, "new_column_name");
        String expression = YamlUtils.getString(config, "expression");

        if (newColumnName != null) {
            withColumnBuilder.setNewColumnName(newColumnName);
        }
        if (expression != null) {
            withColumnBuilder.setExpression(expression);
        }

        workflowBuilder.setWithColumn(withColumnBuilder.build());
    }
}
