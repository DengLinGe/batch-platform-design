import org.apache.spark.sql.SparkSession

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -01 -23 18:07
 * @Modified By:
 */
class TestSpark {


}

object TestSpark {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SparkExample")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    // 创建一个简单的 DataFrame
    val data = Seq((1, "Alice"), (2, "Bobq"))
    val columns = Seq("id", "name")
    val df = data.toDF(columns: _*)

    // 显示 DataFrame
    df.show()

    // 停止 SparkSession
    spark.stop()
  }
}
