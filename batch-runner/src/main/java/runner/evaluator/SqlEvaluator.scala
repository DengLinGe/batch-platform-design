package runner.evaluator

import entity.PipelineProto
import org.apache.spark.sql.{DataFrame, SparkSession}
import runner.RunnerContext

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -18 13:18
 * @Modified By:
 */
// SqlEvaluator 类
class SqlEvaluator(config: PipelineProto.Workflow.Sql, uid: String) extends Evaluator {
  override def evaluate(context: RunnerContext): Unit = {
    val inputDf: DataFrame = context.getDataFrame
    val sparkSession: SparkSession = context.getSparkSession
    inputDf.createOrReplaceTempView("tempTable")
    val resultDf = sparkSession.sql(config.getQuery)
    context.setDataFrame(resultDf)
  }
}
