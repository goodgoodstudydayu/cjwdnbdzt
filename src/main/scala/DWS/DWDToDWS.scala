package DWS

import org.apache.spark.sql.SparkSession

object DWDToDWS {
  def main(args: Array[String]): Unit = {
    val session = SparkSession.builder().appName("DWDToDWS").master("local[*]").getOrCreate()
    session.conf.set("hive.exec.dynamic.partition.mode", "nonstrict")
    session.sql("use ceshi")
    session.sql(
      """
        |create external table if not exists ceshi.dws_fly(
        |eventtm string,
        |`number`	string,
        |`name`	string,
        |`gender`	string,
        |`age`	int,
        |`code`	string,
        |`comcode`	string,
        |`s_airport`	string,
        |`a_airport`	string,
        |`s_data`	string,
        |`a_data`	string,
        |`status`	string,
        |`distance`	DECIMAL(12,2),
        |`s_time`	string,
        |`a_time`	string
        |)PARTITIONED BY (evevntdate string)
        |ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
        |LOCATION '/user/hive/warehouse/ceshi.db/'
        |""".stripMargin)
    session.sql(
      """
        |INSERT OVERWRITE TABLE ceshi.dws_fly partition(evevntdate)
        |select
        |nvl(t1.eventtm,fly.eventtm) eventtm,
        |nvl(t1.number,fly.number) number,
        |nvl(t1.name,fly.name) name,
        |nvl(t1.gender,fly.gender) gender,
        |nvl(t1.age,fly.age) age,
        |nvl(t1.code,fly.code) code,
        |nvl(t1.comcode,fly.comcode),
        |nvl(t1.s_airport,fly.s_airport) s_airport,
        |nvl(t1.a_airport,fly.a_airport) a_airport,
        |nvl(t1.s_data,fly.s_data) s_data,
        |nvl(t1.a_data,fly.a_data) a_data,
        |nvl(t1.status,fly.status) status,
        |nvl(t1.distance,fly.distance) distance,
        |nvl(t1.s_time,fly.s_time) s_time,
        |nvl(t1.a_time,fly.a_time) a_time,
        |substring(nvl(t1.eventtm,fly.eventtm),0,8) as evevntdate
        |from dws_fly fly
        |full join (select
        |book.eventtm,
        |book.number,
        |book.name,
        |book.gender,
        |book.age,
        |airline.code,
        |substring(airline.code,0,2) as	comcode,
        |airline.s_airport,
        |airline.a_airport,
        |airline.s_data,
        |airline.a_data,
        |case when T2.status_time["arrivel"] is not null then 'arrivel'
        |when T2.status_time["flight"] is not null then 'flight'
        |else 'waiting' end as status,
        |T2.status_time["flight"] s_time,
        |T2.status_time["arrivel"] a_time,
        |distance
        |from dwd_book book
        |join dwd_airline airline on book.number=airline.number
        |join dwd_distance distance on airline.s_airport=distance.s_airport and airline.a_airport=distance.a_airport
        |left join (
        |select
        |number,
        |str_to_map(CONCAT_WS(',',collect_set(CONCAT_WS(':',status,eventtm))),',') status_time
        |from dwd_flt
        |group by number
        |) T2 on book.number=T2.number)t1
        |on fly.number=t1.number and fly.s_airport=t1.s_airport
        |""".stripMargin)
    session.close()
  }
}
