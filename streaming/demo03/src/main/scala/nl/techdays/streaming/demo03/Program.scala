package nl.techdays.streaming.demo03

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{StreamingContext}
import org.apache.spark.streaming.Durations._
import org.apache.spark.streaming.eventhubs.Implicits._

object Program {
  def main(args: Array[String]) = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("sample-streaming-app")

    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, seconds(10))

    //TODO: Write your azure code here.

    val streamParams = Map(
      "eventhubs.policyname" -> "SparkListenerPolicy",
      "eventhubs.policykey" -> "vlRIdVNaatys/r64ODGXpeWi0Na+hOFDP8N+fVjbC6I=",
      "eventhubs.namespace" -> "techdays-demo-ns",
      "eventhubs.name" -> "techdays-demo",
      "eventhubs.partition.count" -> "4",
      "eventhubs.checkpoint.dir" -> "checkpointdir",
      "eventhubs.checkpoint.interval" -> "1000"
    )

    val stream = ssc.unionedEventHubStream(streamParams)

    printf("Number of events %d", stream.count())

    ssc.start()
    ssc.awaitTermination()
  }
}
