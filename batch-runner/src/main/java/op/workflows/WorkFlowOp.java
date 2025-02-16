package op.workflows;

import entity.PipelineProto;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -16 15:44
 * @Modified By:
 */
public interface WorkFlowOp {

    void create(Map<String, Object> config, PipelineProto.Workflow.Builder workflowBuilder);
}
