package runner.evaluator

import org.apache.spark.sql.SparkSession
import runner.RunnerContext

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -17 16:46
 * @Modified By:
 */
abstract class Evaluator {
  def evaluate(context: RunnerContext): Unit

}