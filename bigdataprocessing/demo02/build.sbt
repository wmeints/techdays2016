name := "demo02"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += Resolver.mavenLocal

libraryDependencies ++= {
  val sparkVersion = "1.6.1"

  Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "com.microsoft.jdbc" % "jdbc42" % "6.0"
  )
}
