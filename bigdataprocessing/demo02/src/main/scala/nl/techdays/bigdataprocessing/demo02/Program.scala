import java.util.{Date, Properties}

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

case class Measurement(date: java.util.Date, weight: Int, temperature: Double)

object Program {
  def main(args: Array[String]) = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("demo02")
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)

    val username = ""
    val password = ""

    val connectionString = s"jdbc:sqlserver://baby-stats.database.windows.net:1433;databaseName=baby-stats;userName=$username;password=$password"
    val tableName = "measurement"

    val records = sqlContext.read.jdbc(connectionString, tableName, new Properties())

    //TODO: Show demo code
  }
}