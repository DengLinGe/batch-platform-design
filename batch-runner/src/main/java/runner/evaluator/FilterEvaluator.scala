package runner.evaluator

import entity.PipelineProto
import org.apache.spark.sql.{Column, DataFrame}
import runner.RunnerContext

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -18 13:20
 * @Modified By:
 */
// FilterEvaluator 类
class FilterEvaluator(config: PipelineProto.Workflow.Filter, uid: String) extends Evaluator {
  override def evaluate(context: RunnerContext): Unit = {
    val inputDf: DataFrame = context.getDataFrame
    val resultDf = inputDf.filter(config.getCondition)
    context.setDataFrame(resultDf)
  }
}