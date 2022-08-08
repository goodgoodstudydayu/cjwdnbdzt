import org.apache.spark.sql.SparkSession

object etlTable {
  def main(args: Array[String]): Unit = {
    val session = SparkSession.builder().appName("etlTable").master("local[*]").getOrCreate()

  }
}
