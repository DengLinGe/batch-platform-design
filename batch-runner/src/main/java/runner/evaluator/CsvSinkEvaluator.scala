package runner.evaluator

import entity.PipelineProto
import runner.RunnerContext

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -18 13:24
 * @Modified By:
 */
class CsvSinkEvaluator(config: PipelineProto.Sink.CsvSink, uid: String) extends Evaluator {
  override def evaluate(context: RunnerContext): Unit = {
    val inputDf = context.getDataFrame
    inputDf.write
      .option("header", config.getHeader)
      .option("delimiter", config.getDelimiter)
      .csv(config.getPath)
  }
}