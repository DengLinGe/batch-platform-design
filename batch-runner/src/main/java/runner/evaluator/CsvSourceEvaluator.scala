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
// CsvSourceEvaluator 类
class CsvSourceEvaluator(config: PipelineProto.Source.CSV, uid: String) extends Evaluator {
  override def evaluate(context: RunnerContext): Unit = {
    val sparkSession: SparkSession = context.getSparkSession
    // 具体实现逻辑
    val df = sparkSession.read
      .option("header", config.getHeader)
      .option("delimiter", config.getDelimiter)
      .option("quote", config.getQuote)
      .option("escape", config.getEscape)
      .csv(config.getPath)
    context.setDataFrame(df)
  }
}
