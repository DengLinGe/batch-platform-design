package framework.op.workflows;

import entity.PipelineProto;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -19 0:24
 * @Modified By:
 */
public class ShowOp implements WorkFlowOp{

    @Override
    public void create(Map<String, Object> config, PipelineProto.Workflow.Builder workflowBuilder) {
        PipelineProto.Workflow.Show.Builder showBuilder = PipelineProto.Workflow.Show.newBuilder();
        int numRows = (Integer)config.getOrDefault("numRows", 10);
        showBuilder.setNumRows(numRows);
        workflowBuilder.setShow(showBuilder.build());
    }
}
