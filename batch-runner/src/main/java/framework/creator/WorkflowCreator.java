package framework.creator;

import entity.PipelineProto;
import framework.op.workflows.*;
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
public class WorkflowCreator implements Creator {
    @Override
    public void createPipeline(PipelineProto.Pipeline.Builder pipelineBuilder, Map<String, Object> config) {
        List<Map<String, Object>> listMap = YamlUtils.getListMap(config, WORKFLOWS);

        if (listMap == null || listMap.isEmpty()) {
            throw new IllegalArgumentException("Workflows is empty.");
        }

        for (Map<String, Object> map : listMap) {
            PipelineProto.Workflow.Builder workflowBuilder = PipelineProto.Workflow.newBuilder();
            // 设置 uid
            workflowBuilder.setUid(YamlUtils.getString(map, UID));
            // 设置 input_ids
            workflowBuilder.addAllInput(YamlUtils.getList(map, INPUT));

            // 设置是否存储
            boolean isStore = YamlUtils.getBoolean(map, "is_store");
            if (isStore) {
                workflowBuilder.setIsStore(true);
            }


            // 根据 operator_type 关键字进行不同 framework.op 的填充
            switch (Objects.requireNonNull(YamlUtils.getString(map, OPERATOR_TYPE))) {
                case "select":
                    new SelectOp().create(map, workflowBuilder);
                    break;
                case "filter":
                    new FilterOp().create(map, workflowBuilder);
                    break;
                case "groupBy":
                    new GroupByOp().create(map, workflowBuilder);
                    break;
                case "join":
                    new JoinOp().create(map, workflowBuilder);
                    break;
                case "with_column":
                    new WithColumnOp().create(map, workflowBuilder);
                    break;
                case "sql":
                    new SqlOp().create(map, workflowBuilder);
                    break;
                case "user_defined":
                    new UserDefinedOp().create(map, workflowBuilder);
                    break;
                case SHOW:
                    new ShowOp().create(map, workflowBuilder);
                    break;
                case TEMP_STORAGE:
                    workflowBuilder.setTempStorage(PipelineProto.Workflow.TempStorage.newBuilder().build());
                    break;
//                case "aggregate":
//                    new AggregateOp().create(map, workflowBuilder);
//                    break;
//                case "sort":
//                    new SortOp().create(map, workflowBuilder);
//                    break;
//                case "window":
//                    new WindowOp().create(map, workflowBuilder);
//                    break;
//                case "drop":
//                    new DropOp().create(map, workflowBuilder);
//                    break;
                default:
                    throw new IllegalArgumentException("Unknown operator type: " + YamlUtils.getString(map, OPERATOR_TYPE));
            }

            pipelineBuilder.addWorkflows(workflowBuilder.build());
        }
    }
}
