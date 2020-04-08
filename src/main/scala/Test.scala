import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object Test {
  def main(args : Array[String]): Unit = {
    var conf = new SparkConf().setAppName("Top 5 Lap Average Drivers").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val sqlContext = new SQLContext(sc)
    val driverLapAvgTop = new DriverLapAvgTop
    val dt = driverLapAvgTop.lapAvgTop(sqlContext)
    dt.show()
    dt.repartition(1)
      .write.csv(path="output\\driverTopAvgLaps.csv")

  }

}
