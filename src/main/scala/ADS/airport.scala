package ADS

import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel

object airport {
  def main(args: Array[String]): Unit = {
    val session = SparkSession.builder().appName("DWDToDWS").master("local[*]").getOrCreate()
    session.conf.set("hive.exec.dynamic.partition.mode","nonstrict")
    session.sql("use ceshi")
    val dws_fly = session.sql("select * from dws_fly").toDF()
      .persist(StorageLevel.MEMORY_AND_DISK)

    //机场维度每日出行人数
    dws_fly.filter(_.getAs[String]("s_time")!=null).groupBy("evevntdate","s_airport").count().show
    //机场维度每日到达人数
    dws_fly.filter(row=>{
      val status=row.getAs[String]("status")
      "arrivel".equals(status)
    }).groupBy("evevntdate","a_airport").count().show
    //航司维度每日出行人数
    dws_fly.filter(_.getAs[String]("s_time")!=null).groupBy("evevntdate","comcode").count().show
    //航司维度每日到达人数
    dws_fly.filter(row=>{
      val status=row.getAs[String]("status")
      "arrivel".equals(status)
    }).groupBy("evevntdate","comcode").count().show
    //旅客总里程数
    dws_fly.filter(row=>{
      val status=row.getAs[String]("status")
      "arrivel".equals(status)
    }).groupBy("number").sum("distance").show

    session.close()

  }
}
