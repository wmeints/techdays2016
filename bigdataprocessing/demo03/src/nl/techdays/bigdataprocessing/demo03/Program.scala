package nl.techdays.bigdataprocessing.demo03

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.sql.hive.HiveContext

case class Average(dimension: String, average: Double)

object Program {
  def main(args: Array[String]) = {
    val conf = new SparkConf().setAppName("adl-sample-app")
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)

    import sqlContext.implicits._

    val measurements = sqlContext.sql("SELECT * FROM measurements")

    measurements
      .map(x => (x.getAs[String]("dimension"), x.getAs[Double]("value")))
      .reduceByKey((left, right) => (left + right) / 2)
      .map { case (dimension, average) => Average(dimension,average) }
      .toDF()
      .write.mode(SaveMode.Append).saveAsTable("averages")
  }
}
