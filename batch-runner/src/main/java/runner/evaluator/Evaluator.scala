package runner.evaluator

import runner.RunnerContext

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -17 16:46
 * @Modified By:
 */
trait Evaluator {

  def evaluate(runnerContext: RunnerContext): Unit

}
