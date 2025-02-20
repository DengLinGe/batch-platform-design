package runner.evaluator.source

import entity.PipelineProto
import entity.PipelineProto.Source
import org.apache.spark.sql.SparkSession
import runner.RunnerContext
import runner.evaluator.Evaluator

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -18 13:21
 * @Modified By:
 */
// MysqlSourceEvaluator 类
class MysqlSourceEvaluator(source: PipelineProto.Source, uid: String) extends Evaluator {
  override def evaluate(context: RunnerContext): Unit = {
    val sparkSession: SparkSession = context.getSparkSession
    val config: Source.Mysql = source.getMysql
    val df = sparkSession.read
      .format("jdbc")
      .option("url", config.getUrl)
      .option("driver", config.getDriver)
      .option("dbTable", config.getDbTable)
      .option("user", config.getUser)
      .option("password", config.getPassword)
      .load()

    df.show(10)

    context.setDataFrame(df)
    if (source.getIsStore) {
      context.setStoreMap(uid, df)
    }
  }
}