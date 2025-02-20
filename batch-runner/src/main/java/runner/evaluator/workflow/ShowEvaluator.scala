package runner.evaluator.workflow

import entity.PipelineProto
import entity.PipelineProto.Workflow
import org.apache.spark.sql.DataFrame
import runner.RunnerContext
import runner.evaluator.Evaluator

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -19 0:33
 * @Modified By:
 */
class ShowEvaluator (workflow: PipelineProto.Workflow, uid: String) extends Evaluator {

  override def evaluate(context: RunnerContext): Unit = {

    val config: Workflow.Show = workflow.getShow
    
    val frame: DataFrame = context.getDataFrame
    val numRows: Int = config.getNumRows
    frame.show(numRows)
  }
}
