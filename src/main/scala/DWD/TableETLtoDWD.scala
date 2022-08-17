package DWD

import org.apache.spark.sql.SparkSession

import java.text.SimpleDateFormat
import java.util.Date

object TableETLtoDWD {
  def main(args: Array[String]): Unit = {
    val session = SparkSession.builder().appName("TableETLtoDWD").master("local[*]").getOrCreate()
    session.sql("set hive.exec.dynamic.partition=true")
    session.sql("set hive.exec.dynamic.partition.mode=nonstrict")
    session.sql("use ceshi")
    val airline = session.sql("select * from ods_airline").toDF()
    val book = session.sql("select * from ods_book").toDF()
    val flt = session.sql("select * from ods_flt").toDF()
    session.sql("select * from dwd_distance").show

    val dwdAirLine = airline.filter(row => {
      val eventtm = row.getAs[String]("eventtm")
      val event = row.getAs[String]("event")
      val number = row.getAs[String]("number")
      val code = row.getAs[String]("code")
      val s_airport = row.getAs[String]("s_airport")
      val a_airport = row.getAs[String]("a_airport")
      val s_data = row.getAs[String]("s_data")
      val a_data = row.getAs[String]("a_data")
      val format = new SimpleDateFormat("yyyyMMdd")
      //判断每一字段都不为空
      var flag = false
      if (eventtm != null && event != null && number != null && code != null &&
        s_airport != null && a_airport != null && s_data != null && a_data != null) {
        if (isDate(eventtm.substring(0, 8))) {
          flag = true
        }
      }
      flag
    }
    ).withColumn("eventdate", col = airline("eventtm").substr(1, 8))
    dwdAirLine.show
    dwdAirLine.write
      .mode("overwrite")
      //      .partitionBy("eventdate")
      .insertInto("dwd_airline")

    val dwdBook = book.filter(row => {
      val eventtm = row.getAs[String]("eventtm")
      val event = row.getAs[String]("event")
      val name = row.getAs[String]("name")
      val number = row.getAs[String]("number")
      val gender = row.getAs[String]("gender")
      val age = row.getAs[Int]("age")
      var flag = false
      if (eventtm != null && event != null && number != null && name != null && gender != null && age > 0L && age < 150) {
        if (isDate(eventtm.substring(0, 8))) {
          flag = true
        }
      }
      flag
    }).withColumn("eventdate", col = book("eventtm").substr(1, 8))
    dwdBook.show
    dwdBook.write.option("/lixian/DWD/book", "/lixian/DWDText/book")
      .mode("overwrite")
      //      .partitionBy("eventdate")
      .format("Hive").insertInto("dwd_book")

    val dwdFlt = flt.filter(row => {
      val eventtm = row.getAs[String]("eventtm")
      val event = row.getAs[String]("event")
      val number = row.getAs[String]("number")
      val status = row.getAs[String]("status")
      var flag = false
      if (eventtm != null && event != null && number != null && status != null) {
        if (isDate(eventtm.substring(0, 8))) {
          flag = true
        }
      }
      flag
    }).withColumn("eventdate", col = flt("eventtm").substr(1, 8))
    dwdFlt.show
    dwdFlt.write
      .mode("overwrite")
      //      .partitionBy("eventdate")
      .format("Hive").insertInto("dwd_flt")
    session.close()
  }

  //def main(args: Array[String]): Unit =
  def isDate(dateStr: String): Boolean = {
    val format = new SimpleDateFormat("yyyyMMdd")
    val newtime: Date = format.parse(dateStr)
    format.format(newtime).equals(dateStr)
  }
}
