package runner.evaluator

import entity.PipelineProto
import org.apache.spark.sql.SparkSession
import runner.RunnerContext

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -18 13:21
 * @Modified By:
 */
// MysqlSourceEvaluator 类
class MysqlSourceEvaluator(config: PipelineProto.Source.Mysql, uid: String) extends Evaluator {
  override def evaluate(context: RunnerContext): Unit = {

    val df = sparkSession.read
      .format("jdbc")
      .option("url", config.getUrl)
      .option("driver", config.getDriver)
      .option("dbtable", config.getDbName)
      .option("user", config.getUser)
      .option("password", config.getPassword)
      .load()
    context.setDataFrame( df)
  }
}