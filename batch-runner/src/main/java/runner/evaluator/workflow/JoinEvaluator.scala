package runner.evaluator.workflow

import entity.PipelineProto
import entity.PipelineProto.Workflow
import org.apache.spark.sql.DataFrame
import runner.RunnerContext
import runner.evaluator.Evaluator

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -18 22:28
 * @Modified By:
 */
// JoinEvaluator 类
class JoinEvaluator(workflow: PipelineProto.Workflow, uid: String) extends Evaluator {
  override def evaluate(context: RunnerContext): Unit = {


    // 获取当前表的 DataFrame
    val currentDf: DataFrame = context.getDataFrame

    val config: Workflow.Join = workflow.getJoin

    // 获取要连接的另一个表的 DataFrame
    val otherTableId = config.getOtherTableId
    val otherDf: DataFrame = context.getStoreMap(otherTableId)

    // 获取连接类型
    val joinType = config.getJoinType

    // 获取连接条件
    val onCondition: String = config.getOnCondition

    // 执行连接操作
    val resultDf = currentDf.join(otherDf, onCondition, joinType)

    // 将结果存储回 RunnerContext
    context.setDataFrame(resultDf)
  }
}