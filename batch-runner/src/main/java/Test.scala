import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import scala.collection.immutable.Seq

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -20 13:59
 * @Modified By:
 */
object Test {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("GroupByAggWithStringExample")
      .master("local[*]")
      .getOrCreate()


    import spark.implicits._
    val data = Seq(
      (1, 100, "A"),
      (2, 200, "B"),
      (3, 150, "A"),
      (4, 250, "B")
    )
    val df = data.toDF("id", "amount", "category")
    df.groupBy("category").agg(sum("amount").as("sum_amount")).show()
  }

}
