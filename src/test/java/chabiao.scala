import org.apache.spark.sql.SparkSession

object chabiao {
  def main(args: Array[String]): Unit = {
    val session = SparkSession.builder().appName("DWDToDWS").master("local[*]").getOrCreate()
    //session.conf.set("hive.exec.dynamic.partition.mode","nonstrict")
    session.sql("select * from ceshi.dwd_distance").show
    session.sql("select * from ceshi.dwd_airline").show
    session.sql("select * from ceshi.dwd_flt").show
    session.sql("select * from ceshi.dwd_book").show
    session.sql("select * from ceshi.dws_fly").show
//    session.sql("drop table ceshi.ods_airline")
//    session.sql("drop table ceshi.dwd_distance")
//    session.sql("drop table ceshi.ods_flt")
//    session.sql("drop table ceshi.ods_book")
//    session.sql("drop table ceshi.dwd_airline")
//    session.sql("drop table ceshi.dwd_distance")
//    session.sql("drop table ceshi.dwd_flt")
//    session.sql("drop table ceshi.dwd_book")
//    session.sql("drop table ceshi.dws_fly")
    session.close()
  }
}
