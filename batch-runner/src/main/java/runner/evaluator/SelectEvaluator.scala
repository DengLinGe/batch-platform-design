package runner.evaluator

import entity.PipelineProto
import org.apache.spark.sql.DataFrame
import runner.RunnerContext

import scala.jdk.CollectionConverters.asScalaBufferConverter

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -18 13:18
 * @Modified By:
 */
// SelectEvaluator 类
class SelectEvaluator(config: PipelineProto.Workflow.Select, uid: String) extends Evaluator {
  override def evaluate(context: RunnerContext): Unit = {
    val inputDf: DataFrame = context.getDataFrame
    val columns: Array[String] = config.getColumnsList.asScala.toArray
    val resultDf: DataFrame = inputDf.select(columns.head, columns.tail: _*)
    context.setDataFrame(resultDf)
  }
}