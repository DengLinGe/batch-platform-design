package runner;

import entity.Node;
import entity.PipelineProto;
import runner.evaluator.*;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -17 16:41
 * @Modified By:
 */
public class Translator {

    public Evaluator translate(Node node) {
        switch (node.operatorType) {
            case "sql":
                PipelineProto.Workflow.Sql sqlConfig = (PipelineProto.Workflow.Sql) node.config;
                return new SqlEvaluator(sqlConfig, node.uid);
            case "select":
                PipelineProto.Workflow.Select selectConfig = (PipelineProto.Workflow.Select) node.config;
                return new SelectEvaluator(selectConfig, node.uid);
            case "filter":
                PipelineProto.Workflow.Filter filterConfig = (PipelineProto.Workflow.Filter) node.config;
                return new FilterEvaluator(filterConfig, node.uid);
            case "mysql_source":
                PipelineProto.Source.Mysql mysqlConfig = (PipelineProto.Source.Mysql) node.config;
                return new MysqlSourceEvaluator(mysqlConfig, node.uid);
            case "csv_source":
                PipelineProto.Source.CSV csvConfig = (PipelineProto.Source.CSV) node.config;
                return new CsvSourceEvaluator(csvConfig, node.uid);
            case "jdbc_sink":
                PipelineProto.Sink.MysqlSink jdbcConfig = (PipelineProto.Sink.MysqlSink) node.config;
                return new MysqlSinkEvaluator(jdbcConfig, node.uid);
            case "csv_sink":
                PipelineProto.Sink.CsvSink csvSinkConfig = (PipelineProto.Sink.CsvSink) node.config;
                return new CsvSinkEvaluator(csvSinkConfig, node.uid);
            default:
                throw new IllegalArgumentException("Unknown operator type: " + node.operatorType);
        }
    }
}
