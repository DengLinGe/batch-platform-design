package runner.evaluator

import entity.PipelineProto
import runner.RunnerContext

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -18 13:23
 * @Modified By:
 */
class MysqlSinkEvaluator (config: PipelineProto.Sink.MysqlSink, uid: String) extends Evaluator {
  override def evaluate(context: RunnerContext): Unit = {
    val inputDf = context.getDataFrame
    inputDf.write
      .format("jdbc")
      .option("url", config.getUrl)
      .option("driver", config.getDriver)
      .option("dbtable", config.getTableName)
      .option("user", config.getUser)
      .option("password", config.getPassword)
      .mode("overwrite")
      .save()
  }
}
