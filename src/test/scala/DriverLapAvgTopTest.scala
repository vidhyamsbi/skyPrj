import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
class DriverLapAvgTopTest extends FunSuite with BeforeAndAfter {

  var driverLapAvgTop: DriverLapAvgTop = _
  var conf : SparkConf = _
  var sc : SparkContext  = _
  var sqlContext : SQLContext  = _

  before {
    conf = new SparkConf().setAppName("Top 5 Lap Average Drivers").setMaster("local[*]")
    sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    sqlContext = new SQLContext(sc)
    driverLapAvgTop = new DriverLapAvgTop
  }

  test("Top 5 Lap Average Drivers") {
    var dt = driverLapAvgTop.lapAvgTop(sqlContext)
    assert(dt.count() == 3)
    assert(dt.first() != null)

  }


}
