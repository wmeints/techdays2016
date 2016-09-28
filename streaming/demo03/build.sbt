name := "demo03"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "spark-eventhubs" at "https://raw.github.com/hdinsight/spark-eventhubs/maven-repo"

libraryDependencies ++= {
  val sparkVersion = "2.0.0"

  Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-streaming" % sparkVersion,
    "com.microsoft.azure" % "spark-streaming-eventhubs_2.10" % "1.1.0"
  )
}