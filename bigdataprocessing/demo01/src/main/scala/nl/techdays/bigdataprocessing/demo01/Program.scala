package nl.techdays.bigdataprocessing.demo01

import org.apache.spark.{SparkConf, SparkContext}

object Program {
  def main(args: Array[String]) = {
    // Configure the application so that it runs on the local master and has a proper name.
    // This makes debugging a whole lot easier ;-)
    val conf = new SparkConf()
      .setAppName("sample-app")
      .setMaster("local[*]")

    // The spark context is used to schedule tasks on the cluster.
    // Create just one of it at the start of the application.
    val sc = new SparkContext(conf)

    //TODO: Enter some code here
  }
}
