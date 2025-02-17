package runner

import org.apache.spark.sql.DataFrame

class RunnerContext {
  private var currentDataFrame: DataFrame = _

  /**
   * 设置当前的 DataFrame
   * @param dataFrame 要设置的 DataFrame
   */
  def setDataFrame(dataFrame: DataFrame): Unit = {
    currentDataFrame = dataFrame
  }

  /**
   * 获取当前的 DataFrame
   * @return 当前存储的 DataFrame，如果未设置则返回 null
   */
  def getDataFrame: DataFrame = {
    currentDataFrame
  }
}