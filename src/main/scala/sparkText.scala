import org.apache.spark.sql.SparkSession

object sparkText {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]")
      .config("spark.hadoop.fs.defaultFS","hdfs://hadoop101:8020")
      .config("hive.metastore.uris","thrift://hadoop101:9083")
      .enableHiveSupport()
      .getOrCreate()
    spark.sql("show databases").show
    spark.stop()
  }
}
