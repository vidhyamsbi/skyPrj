import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

class DriverLapAvgTop {

  def lapAvgTop(sqlContext:SQLContext): DataFrame ={
    import sqlContext.implicits._

    val drvrDF= sqlContext.read.format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load("input\\data.csv")

    drvrDF.createOrReplaceTempView("DriverLaps")  //.registerTempTable("DriverLaps")
    val dt = sqlContext.sql("select Driver,SUM(LapTime)/count(*) AverageLaptime " +
      "                               from DriverLaps " +
      "                               group by Driver " +
      "                               Order by AverageLaptime").limit(3)

    return dt
  }


}
