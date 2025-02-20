package runner

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.{Logger, LoggerFactory}

class RunnerContext {

  val logger: Logger = LoggerFactory.getLogger(classOf[RunnerContext])

  private val sparkSession: SparkSession = SparkSession.builder()
    .appName("BatchRunner")
    .master("local")
    .getOrCreate()
  private var currentDataFrame: DataFrame = _

  private val storeMap = scala.collection.mutable.Map[String, DataFrame]()

  /**
   * 设置 storeMap, 作为临时数据存储使用
   * @param key DataFrame 的 key
   * @param dataFrame 要设置的 DataFrame
   */
  def setStoreMap(key: String, dataFrame: DataFrame): Unit = {
    storeMap(key) = dataFrame
  }

  /**
   * 获取 storeMap, 作为临时数据存储使用
   * @param key DataFrame 的 key
   * @return 当前存储的 DataFrame，如果未设置则返回 null
   */
  def getStoreMap(key: String): DataFrame = {
    if (!storeMap.contains(key)) {
      logger.error("Key not found in storeMap")
      throw new Exception("Key not found in storeMap")
    }
    storeMap(key)
  }

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

  /**
   * 获取 SparkSession
   * */
  def getSparkSession: SparkSession = {
    sparkSession
  }
}