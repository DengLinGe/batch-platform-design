package runner;

import entity.Node;
import entity.PipelineProto;
import runner.evaluator.*;
import runner.evaluator.sink.CsvSinkEvaluator;
import runner.evaluator.sink.MysqlSinkEvaluator;
import runner.evaluator.source.CsvSourceEvaluator;
import runner.evaluator.source.MysqlSourceEvaluator;
import runner.evaluator.workflow.*;

import static entity.Constants.*;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -17 16:41
 * @Modified By:
 */
public class Translator {

    public Evaluator translate(Node node) {
        switch (node.operatorType) {
            case SQL:
                PipelineProto.Workflow sqlConfig = (PipelineProto.Workflow) node.config;
                return new SqlEvaluator(sqlConfig, node.uid);
            case SELECT:
                PipelineProto.Workflow.Select selectConfig = (PipelineProto.Workflow.Select) node.config;
                return new SelectEvaluator(selectConfig, node.uid);
            case FILTER:
                PipelineProto.Workflow.Filter filterConfig = (PipelineProto.Workflow.Filter) node.config;
                return new FilterEvaluator(filterConfig, node.uid);
            case JOIN:
                PipelineProto.Workflow joinConfig = (PipelineProto.Workflow) node.config;
                return new JoinEvaluator(joinConfig, node.uid);
            case SHOW:
                PipelineProto.Workflow showConfig = (PipelineProto.Workflow) node.config;
                return new ShowEvaluator(showConfig, node.uid);
            case GROUPBY:
                PipelineProto.Workflow groupByConfig = (PipelineProto.Workflow) node.config;
                return new GroupByEvaluator(groupByConfig, node.uid);
            case TEMP_STORAGE:
                return new TempStorageEvaluator(null, node.uid);
            case MYSQL_SOURCE:
                PipelineProto.Source mysqlConfig = (PipelineProto.Source) node.config;
                return new MysqlSourceEvaluator(mysqlConfig, node.uid);
            case CSV_SOURCE:
                PipelineProto.Source csvConfig = (PipelineProto.Source) node.config;
                return new CsvSourceEvaluator(csvConfig, node.uid);
            case MYSQL_SINK:
                PipelineProto.Sink.MysqlSink jdbcConfig = (PipelineProto.Sink.MysqlSink) node.config;
                return new MysqlSinkEvaluator(jdbcConfig, node.uid);
            case CSV_SINK:
                PipelineProto.Sink.CsvSink csvSinkConfig = (PipelineProto.Sink.CsvSink) node.config;
                return new CsvSinkEvaluator(csvSinkConfig, node.uid);
            default:
                throw new IllegalArgumentException("Unknown operator type: " + node.operatorType);
        }
    }
}
