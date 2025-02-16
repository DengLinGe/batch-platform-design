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
public class SqlOp implements WorkFlowOp{
    @Override
    public void create(Map<String, Object> config, PipelineProto.Workflow.Builder workflowBuilder) {
        PipelineProto.Workflow.Sql.Builder sqlBuilder = PipelineProto.Workflow.Sql.newBuilder();
        String query = YamlUtils.getString(config, "query");
        if (query != null) {
            sqlBuilder.setQuery(query);
        }
        workflowBuilder.setSql(sqlBuilder.build());
    }
}
