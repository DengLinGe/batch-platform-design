package runner.evaluator.workflow

import entity.PipelineProto
import entity.PipelineProto.Workflow
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Column, DataFrame}
import runner.RunnerContext
import runner.evaluator.Evaluator

import scala.collection.JavaConverters._
import scala.collection.mutable
/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -20 14:15
 * @Modified By:
 */
class GroupByEvaluator (workflow: PipelineProto.Workflow, uid: String) extends Evaluator {
  override def evaluate(context: RunnerContext): Unit = {
    val inputDf = context.getDataFrame

    val config: Workflow.GroupBy = workflow.getGroupBy
    val columns: mutable.Buffer[String] = config.getColumnsList.asScala

    val aggColumns = config.getAggList.asScala.map { aggConfig =>
      val parts = aggConfig.split("=")
      parts.length match {
        case 2 =>
          val funcName = parts(0)
          val columnName = parts(1)
          getAggFunction(funcName)(col(columnName))
        case 3 =>
          val funcName = parts(0)
          val columnName = parts(1)
          val alias = parts(2)
          getAggFunction(funcName)(col(columnName)).as(alias)
        case _ =>
          throw new IllegalArgumentException(s"Invalid agg configuration: $aggConfig")
      }
    }

    val df: DataFrame = inputDf.groupBy(columns.head, columns.tail: _*).agg(aggColumns.head, aggColumns.tail: _*)

    println("-----------------------------this is groupby-------------------------------")
    df.show(10)
    context.setDataFrame(df)

  }
  // 根据函数名获取聚合函数
  private def getAggFunction(funcName: String): Column => Column = {
    funcName.toLowerCase match {
      case "sum" => sum
      case "count" => count
      case other => throw new IllegalArgumentException(s"Unsupported aggregation function: $other")
    }
  }
}
