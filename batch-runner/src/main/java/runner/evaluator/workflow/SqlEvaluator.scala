package runner.evaluator.workflow

import entity.PipelineProto
import entity.PipelineProto.Workflow
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.{Logger, LoggerFactory}
import runner.RunnerContext
import runner.evaluator.Evaluator

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -18 13:18
 * @Modified By:
 */
// SqlEvaluator 类
class SqlEvaluator(workflow: PipelineProto.Workflow, uid: String) extends Evaluator {
  val logger: Logger = LoggerFactory.getLogger(classOf[SqlEvaluator])
  override def evaluate(context: RunnerContext): Unit = {
    val inputDf: DataFrame = context.getDataFrame
    val sparkSession: SparkSession = context.getSparkSession
    val config: Workflow.Sql = workflow.getSql
    val sqlQuery: String = config.getQuery
    logger.info(s"Executing SQL: $sqlQuery")
    inputDf.createOrReplaceTempView("tempTable")
    val resultDf = sparkSession.sql(sqlQuery)
    context.setDataFrame(resultDf)
  }
}
