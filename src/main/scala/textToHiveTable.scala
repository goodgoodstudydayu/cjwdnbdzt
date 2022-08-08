import org.apache.spark.sql.{DataFrame, SparkSession}

object textToHiveTable {
  def main(args: Array[String]): Unit = {
    val session = SparkSession.builder().appName("textToHiveTable").master("local[*]").getOrCreate()
    //开启动态分区
    session.sql("set hive.exec.dynamic.partition=true")
    session.sql("set hive.exec.dynamic.partition.mode=nonstrict")

//    val df1 = session.read.json("/lixian/airline")
    session.read.json("/lixian/airline").createOrReplaceTempView("tmp_ods_airline")

    session.read.json("/lixian/book").createOrReplaceTempView("tmp_ods_book")
    session.read.json("/lixian/flt").createOrReplaceTempView("tmp_ods_flt")
    session.read.json("/lixian/distance").createOrReplaceTempView("tmp_dwd_distance")

    session.sql("create database if not exists ceshi")
    session.sql("use ceshi")
    session.sql(
      """
        |insert overwrite table ods_airline
        |select
        |eventtm,
        |event,
        |number,
        |code,
        |s_airport,
        |a_airport,
        |s_data,
        |a_data
        | from tmp_ods_airline
        |""".stripMargin)
    session.sql(
      """
        |insert overwrite table ods_book
        |select
        |eventtm,
        |event,
        |name,
        |number,
        |gender,
        |age
        |from tmp_ods_book
        |""".stripMargin)
    session.sql(
      """
        |insert overwrite table ods_flt
        |select
        |eventtm,
        |event,
        |number,
        |status
        |from tmp_ods_flt
        |""".stripMargin)
    session.sql(
      """
        |insert overwrite table dwd_distance
        |select
        |s_airport,
        |a_airport,
        |distance
        |from tmp_dwd_distance
        |""".stripMargin)
//    df1.repartition(1).write.mode("overwrite").insertInto("ods_airline")
//    df2.write.option("/lixian/book","/lixian/book").mode("overwrite").saveAsTable("ods_book")
//    df3.write.option("/lixian/flt","/lixian/flt").mode("overwrite").saveAsTable("ods_flt")
//    df4.write.option("/lixian/flt","/lixian/flt").mode("overwrite").saveAsTable("dwd_distance")
    session.sql("select * from ods_airline").show
    session.sql("select * from ods_book").show
    session.sql("select * from ods_flt").show
    session.sql("select * from dwd_distance").show
    session.stop()
  }
}
