package runner.evaluator.source

import entity.PipelineProto
import org.apache.spark.sql.SparkSession
import org.slf4j.{Logger, LoggerFactory}
import runner.RunnerContext
import runner.evaluator.Evaluator

import java.net.URL

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -18 13:21
 * @Modified By:
 */
// CsvSourceEvaluator 类
class CsvSourceEvaluator(source: PipelineProto.Source, uid: String) extends Evaluator {

  val logger: Logger = LoggerFactory.getLogger(classOf[CsvSourceEvaluator])

  override def evaluate(context: RunnerContext): Unit = {
    val sparkSession: SparkSession = context.getSparkSession



    val config: PipelineProto.Source.CSV = source.getCsv

    // 具体实现逻辑
    logger.info(config.getPath+"-------------------")
    val path: URL = Thread.currentThread().getContextClassLoader.getResource(config.getPath)
    // 检测path是否为空
    if (path == null) {
      logger.error("Path is empty")
    }
//    val csvFilePath = getClass.getResource(config.getPath).getPath
    val df = sparkSession.read
      .option("header", config.getHeader)
      .option("delimiter", config.getDelimiter)
      .option("quote", config.getQuote)
      .option("escape", config.getEscape)
      .option("inferSchema", "true")
      .csv(path.getPath)

    df.show(10)
    context.setDataFrame(df)
    if (source.getIsStore) {
    context.setStoreMap(uid, df)
    }
  }
}
