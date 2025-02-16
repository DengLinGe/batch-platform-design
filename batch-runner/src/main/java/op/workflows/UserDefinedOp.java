package op.workflows;

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
public class UserDefinedOp implements WorkFlowOp{
    @Override
    public void create(Map<String, Object> config, PipelineProto.Workflow.Builder workflowBuilder) {
        PipelineProto.Workflow.UserDefined.Builder userDefinedBuilder = PipelineProto.Workflow.UserDefined.newBuilder();
        String functionName = YamlUtils.getString(config, "function_name");
        List<String> parameters = YamlUtils.getList(config, "parameters");

        if (functionName != null) {
            userDefinedBuilder.setFunctionName(functionName);
        }
        userDefinedBuilder.addAllParameters(parameters);

        workflowBuilder.setUserDefined(userDefinedBuilder.build());
    }
}
