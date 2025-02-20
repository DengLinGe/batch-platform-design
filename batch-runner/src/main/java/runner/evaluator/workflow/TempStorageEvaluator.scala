package runner.evaluator.workflow

import entity.PipelineProto
import org.apache.spark.sql.DataFrame
import runner.RunnerContext
import runner.evaluator.Evaluator

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -19 0:13
 * @Modified By:
 */
class TempStorageEvaluator (config: PipelineProto.Source.Mysql, uid: String) extends Evaluator {
  override def evaluate(context: RunnerContext): Unit = {
    val df: DataFrame = context.getDataFrame
    df.show(4)
    context.setStoreMap(uid, df);

  }

}
