# Azure Data Lake demo
This demo shows how to use Azure Data Lake within your Spark application.
The purpose of this demo is to show you just how flexible Spark is when it
comes to data sources.

For more information on how to use products such as Apache Hive in combination
with Apache Spark and Azure Data Lake check the following links:

 - [Get started with Apache Hive](https://cwiki.apache.org/confluence/display/Hive/GettingStarted)
 - [Link Azure Data Lake to your Spark cluster](https://azure.microsoft.com/en-us/documentation/articles/data-lake-store-hdinsight-hadoop-use-portal/)

## Prerequisites
Make sure that you have a Azure Date Lake instance configured on Azure.
Configure Azure AAD with your Spark cluster so that you have access to the
Azure Data Lake.
[Follow the guide here to set things up correctly](https://azure.microsoft.com/en-us/documentation/articles/data-lake-store-hdinsight-hadoop-use-portal/)

## Setup needed for the demo
To run the demo, open up the Ambari dashboard from the Azure portal and navigate to the Hive view.
You can find the Hive view on your Ambari dashboard by following these steps:

 1. Find your HDInsight cluster on the Azure portal
 2. Select the resource and find the button `Cluster dashboards`
 3. Select the item `HDInsight cluster dashboard`
 4. Find the button with 9 squares to the topright in the navigation bar
 5. From the dropdown select Hive view

Next execute the following commands to create the necessary hive tables
within your Azure data lake:

```
create table measurements (offset bigint, dimension string, value double) location 'adl://<your azure data lake store>/data/measurements';
create table averages (dimension string, value double) location 'adl://<your azure data lake store>/data/averages';
```

You can insert some records into the measurements table using the following command.
Change the values to what you want for your test.

```
INSERT INTO measurements VALUES(1,'temperature', 100000)
```

## Run the demo
Open the code in IntelliJ, right click the project and select
Submit job to HDInsight. Follow the instructions on screen to run the
demo on your HDInsight cluster.

After you're done, check the results using the hive view
on the Ambari dashboard.
