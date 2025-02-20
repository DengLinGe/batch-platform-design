package runner.evaluator.sink

import entity.PipelineProto
import org.slf4j.{Logger, LoggerFactory}
import runner.RunnerContext
import runner.evaluator.Evaluator

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -18 13:23
 * @Modified By:
 */
class MysqlSinkEvaluator (config: PipelineProto.Sink.MysqlSink, uid: String) extends Evaluator {
  val logger: Logger = LoggerFactory.getLogger(classOf[MysqlSinkEvaluator])
  override def evaluate(context: RunnerContext): Unit = {
    val inputDf = context.getDataFrame

    logger.info(s"Writing to MySQL: ${config.getUrl}")
    inputDf.show(4)


    inputDf.write
      .format("jdbc")
      .option("url", config.getUrl)
      .option("driver", config.getDriver)
      .option("dbTable", config.getDbTable)
      .option("user", config.getUser)
      .option("password", config.getPassword)
      .mode("overwrite")
      .save()
  }
}
